package com.nexdom.estoque.br.dto;

import com.nexdom.estoque.br.domain.enums.TipoProduto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long codigo,
        String descricao,
        TipoProduto tipoProduto,
        BigDecimal valorFornecedor,
        Integer quantidadeEstoque
) {}
