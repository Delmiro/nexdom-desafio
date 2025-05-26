package com.nexdom.estoque.br.domain.entity;

import com.nexdom.estoque.br.domain.enums.TipoProduto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProduto tipoProduto;

    @Column(nullable = false)
    private BigDecimal valorFornecedor;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

}
