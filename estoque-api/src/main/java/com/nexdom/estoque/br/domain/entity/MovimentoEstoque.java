package com.nexdom.estoque.br.domain.entity;

import com.nexdom.estoque.br.domain.enums.TipoMovimentacao;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_codigo")
    private Produto produto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorVenda;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(nullable = false)
    private Integer quantidadeMovimentada;

}
