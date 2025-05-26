package com.nexdom.estoque.br.repository;

import com.nexdom.estoque.br.domain.entity.MovimentoEstoque;
import com.nexdom.estoque.br.domain.entity.Produto;
import com.nexdom.estoque.br.domain.enums.TipoMovimentacao;
import com.nexdom.estoque.br.dto.LucroProdutoDTO;
import com.nexdom.estoque.br.dto.MovimentoEstoqueDTO;
import com.nexdom.estoque.br.dto.ProdutoTipoResumoDTO;
import com.nexdom.estoque.br.exception.NegocioException;
import com.nexdom.estoque.br.mapper.MovimentoEstoqueMapper;
import com.nexdom.estoque.br.service.MovimentoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovimentoEstoqueImpl implements MovimentoEstoqueService {

    private final MovimentoEstoqueRepository movimentoEstoqueRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public MovimentoEstoqueDTO registrarMovimento(MovimentoEstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new NegocioException("Produto nãoo encontrado."));

        if (dto.getTipoMovimento().equals(TipoMovimentacao.SAIDA)) {
            int saldoAtual = calcularSaldoProduto(produto);
            if (dto.getQuantidade() > saldoAtual) {
                throw new NegocioException("Quantidadee insuficiente no estoque para saida.");
            }
        }

        MovimentoEstoque movimento = MovimentoEstoqueMapper.toEntity(dto);
        movimento.setProduto(produto);

        MovimentoEstoque salvo = movimentoEstoqueRepository.save(movimento);
        return MovimentoEstoqueMapper.toDTO(salvo);
    }

    @Override
    public List<ProdutoTipoResumoDTO> consultarProdutosPorTipo(String tipo) {
        TipoMovimentacao tipoMovimento;
        try {
            tipoMovimento = TipoMovimentacao.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Tipo de movimento invalido.");
        }

        List<MovimentoEstoque> movimentos = movimentoEstoqueRepository.findByTipoMovimentacao(tipoMovimento);
        Map<Long, ProdutoTipoResumoDTO> resumoMap = new HashMap<>();

        for (MovimentoEstoque mov : movimentos) {
            Long produtoId = mov.getProduto().getCodigo();
            ProdutoTipoResumoDTO resumo = resumoMap.getOrDefault(produtoId, new ProdutoTipoResumoDTO());
            resumo.setProdutoId(produtoId);
            resumo.setNomeProduto(mov.getProduto().getDescricao());

            if (tipoMovimento == TipoMovimentacao.SAIDA) {
                resumo.setQuantidadeSaida(resumo.getQuantidadeSaida() + mov.getQuantidadeMovimentada());
            }
            int saldo = calcularSaldoProduto(mov.getProduto());
            resumo.setQuantidadeDisponivel(saldo);
            resumoMap.put(produtoId, resumo);
        }

        return new ArrayList<>(resumoMap.values());
    }

    @Override
    public List<LucroProdutoDTO> consultarLucroPorProduto() {
        List<Produto> produtos = produtoRepository.findAll();

        List<LucroProdutoDTO> lucros = new ArrayList<>();

        for (Produto produto : produtos) {
            List<MovimentoEstoque> saidas = movimentoEstoqueRepository.findByProdutoAndTipoMovimentacao(produto, TipoMovimentacao.SAIDA);

            int quantidadeTotalSaida = saidas.stream()
                    .mapToInt(MovimentoEstoque::getQuantidadeMovimentada)
                    .sum();

            BigDecimal valorVenda = produto.getValorFornecedor() != null ? produto.getValorFornecedor() : BigDecimal.ZERO;
            BigDecimal valorFornecedor = produto.getValorFornecedor() != null ? produto.getValorFornecedor() : BigDecimal.ZERO;

            BigDecimal lucroUnitario = valorVenda.subtract(valorFornecedor);
            BigDecimal lucroTotal = lucroUnitario.multiply(BigDecimal.valueOf(quantidadeTotalSaida));

            LucroProdutoDTO dto = new LucroProdutoDTO();
            dto.setProdutoId(produto.getCodigo());
            dto.setNomeProduto(produto.getDescricao());
            dto.setQuantidadeTotalSaida(quantidadeTotalSaida);
            dto.setLucroTotal(lucroTotal);

            lucros.add(dto);
        }

        return lucros;
    }

    private int calcularSaldoProduto(Produto produto) {
        int entradas = movimentoEstoqueRepository.findByProdutoAndTipoMovimentacao(produto, TipoMovimentacao.ENTRADA)
                .stream()
                .mapToInt(MovimentoEstoque::getQuantidadeMovimentada)
                .sum();

        int saidas = movimentoEstoqueRepository.findByProdutoAndTipoMovimentacao(produto, TipoMovimentacao.SAIDA)
                .stream()
                .mapToInt(MovimentoEstoque::getQuantidadeMovimentada)
                .sum();

        return entradas - saidas;
    }
}
