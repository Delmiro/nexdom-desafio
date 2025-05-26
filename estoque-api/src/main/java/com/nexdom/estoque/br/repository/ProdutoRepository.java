package com.nexdom.estoque.br.repository;

import com.nexdom.estoque.br.domain.entity.Produto;
import com.nexdom.estoque.br.domain.enums.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoProduto(TipoProduto tipoProduto);

}
