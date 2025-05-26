package com.nexdom.estoque.br.service;

import com.nexdom.estoque.br.domain.enums.TipoProduto;
import com.nexdom.estoque.br.dto.ProdutoDTO;
import java.util.List;

public interface ProdutoService {
    ProdutoDTO salvar(ProdutoDTO dto);
    List<ProdutoDTO> listarTodos();
    ProdutoDTO buscarPorId(Long id);
    void deletar(Long id);
    List<ProdutoDTO> buscarPorTipo(TipoProduto tipo);
}
