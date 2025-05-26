package com.nexdom.estoque.br.repository;

import com.nexdom.estoque.br.domain.entity.Produto;
import com.nexdom.estoque.br.domain.enums.TipoProduto;
import com.nexdom.estoque.br.dto.ProdutoDTO;
import com.nexdom.estoque.br.exception.ProdutoNaoEncontradoException;
import com.nexdom.estoque.br.mapper.ProdutoMapper;
import com.nexdom.estoque.br.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = ProdutoMapper.toEntity(dto);
        return ProdutoMapper.toDTO(produtoRepository.save(produto));
    }

    @Override
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(ProdutoMapper::toDTO)
                .toList();
    }

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoMapper::toDTO)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Override
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public List<ProdutoDTO> buscarPorTipo(TipoProduto tipo) {
        return produtoRepository.findByTipoProduto(tipo).stream()
                .map(ProdutoMapper::toDTO)
                .toList();
    }
}
