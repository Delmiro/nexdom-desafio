package com.nexdom.estoque.br.controller;

import com.nexdom.estoque.br.domain.enums.TipoProduto;
import com.nexdom.estoque.br.dto.ProdutoDTO;
import com.nexdom.estoque.br.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody @Validated ProdutoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(dto));
    }

    @GetMapping
    public List<ProdutoDTO> listar() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscar(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<ProdutoDTO> buscarPorTipo(@PathVariable TipoProduto tipo) {
        return produtoService.buscarPorTipo(tipo);
    }
}
