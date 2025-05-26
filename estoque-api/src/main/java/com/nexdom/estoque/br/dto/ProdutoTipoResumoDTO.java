package com.nexdom.estoque.br.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProdutoTipoResumoDTO {
    private Long produtoId;
    private String nomeProduto;
    private String tipoProduto;
    private Integer quantidadeDisponivel;
    private Integer quantidadeSaida;

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public String getTipoProduto() { return tipoProduto; }
    public void setTipoProduto(String tipoProduto) { this.tipoProduto = tipoProduto; }

    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    public Integer getQuantidadeSaida() { return quantidadeSaida; }
    public void setQuantidadeSaida(Integer quantidadeSaida) { this.quantidadeSaida = quantidadeSaida; }
}
