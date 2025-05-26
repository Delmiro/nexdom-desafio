package com.nexdom.estoque.br.dto;

import java.time.LocalDateTime;

public class MovimentoEstoqueDTO {

    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private String tipoMovimento;
    private LocalDateTime dataMovimento;

    public MovimentoEstoqueDTO() {}

    public MovimentoEstoqueDTO(Long id, Long produtoId, String nomeProduto, Integer quantidade, String tipoMovimento, LocalDateTime dataMovimento) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.tipoMovimento = tipoMovimento;
        this.dataMovimento = dataMovimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public LocalDateTime getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
}
