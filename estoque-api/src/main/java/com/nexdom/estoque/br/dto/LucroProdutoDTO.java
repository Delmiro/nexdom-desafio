package com.nexdom.estoque.br.dto;

import java.math.BigDecimal;

public class LucroProdutoDTO {
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidadeTotalSaida;
    private BigDecimal lucroTotal;

    public LucroProdutoDTO(Long produtoId, String nomeProduto, Integer quantidadeTotalSaida, BigDecimal lucroTotal) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidadeTotalSaida = quantidadeTotalSaida;
        this.lucroTotal = lucroTotal;
    }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Integer getQuantidadeTotalSaida() { return quantidadeTotalSaida; }
    public void setQuantidadeTotalSaida(Integer quantidadeTotalSaida) { this.quantidadeTotalSaida = quantidadeTotalSaida; }

    public BigDecimal getLucroTotal() { return lucroTotal; }
    public void setLucroTotal(BigDecimal lucroTotal) { this.lucroTotal = lucroTotal; }
}
