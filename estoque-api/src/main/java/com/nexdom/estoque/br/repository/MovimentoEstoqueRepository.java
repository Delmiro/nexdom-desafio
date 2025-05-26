package com.nexdom.estoque.br.repository;

import com.nexdom.estoque.br.domain.entity.MovimentoEstoque;
import com.nexdom.estoque.br.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long> {
    List<MovimentoEstoque> findByProduto(Produto produto);
}

