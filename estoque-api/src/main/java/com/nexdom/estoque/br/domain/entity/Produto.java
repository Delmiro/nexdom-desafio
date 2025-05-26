package com.nexdom.estoque.br.domain.entity;

import com.nexdom.estoque.br.domain.enums.TipoProduto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
