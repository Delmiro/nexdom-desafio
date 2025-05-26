package com.nexdom.estoque.br.service;

import com.nexdom.estoque.br.domain.entity.MovimentoEstoque;
import com.nexdom.estoque.br.dto.LucroProdutoDTO;
import com.nexdom.estoque.br.dto.MovimentoEstoqueDTO;
import com.nexdom.estoque.br.dto.ProdutoTipoResumoDTO;

import java.util.List;

public interface MovimentoEstoqueService {
    MovimentoEstoqueDTO registrarMovimento(MovimentoEstoqueDTO dto);
    List<ProdutoTipoResumoDTO> consultarProdutosPorTipo(String tipo);
    List<LucroProdutoDTO> consultarLucroPorProduto();
}
