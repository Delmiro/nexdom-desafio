package com.nexdom.estoque.br.mapper;

import com.nexdom.estoque.br.domain.entity.Produto;
import com.nexdom.estoque.br.dto.ProdutoDTO;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produto p) {
        return new ProdutoDTO(
                p.getCodigo(),
                p.getDescricao(),
                p.getTipoProduto(),
                p.getValorFornecedor(),
                p.getQuantidadeEstoque()
        );
    }

    public static Produto toEntity(ProdutoDTO dto) {
        return Produto.builder()
                .codigo(dto.codigo())
                .descricao(dto.descricao())
                .tipoProduto(dto.tipoProduto())
                .valorFornecedor(dto.valorFornecedor())
                .quantidadeEstoque(dto.quantidadeEstoque())
                .build();
    }
}
