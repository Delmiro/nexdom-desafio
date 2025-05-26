package com.nexdom.estoque.br.controller;

import com.nexdom.estoque.br.domain.enums.TipoProduto;
import com.nexdom.estoque.br.dto.ProdutoDTO;
import com.nexdom.estoque.br.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Salvar novco produto",
            description = "Cria um novo produto no sistemda",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody @Validated ProdutoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(dto));
    }

    @Operation(summary = "Listar todos os produtos",
            description = "Retorna a listaa completa de produtos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de produtos",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class)))
            })
    @GetMapping
    public List<ProdutoDTO> listar() {
        return produtoService.listarTodos();
    }

    @Operation(summary = "Buscar produto por id",
            description = "Retorna os dados do produto identificado pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto encontrado",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            })
    @GetMapping("/{id}")
    public ProdutoDTO buscar(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @Operation(summary = "Deletar produto por id",
            description = "Remove o produto identificado pelo id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
            })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

    @Operation(summary = "Buscar produtos por tipo",
            description = "Retorna a lista de produtos filtrados pelo tipo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de produtos por tipo",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class)))
            })
    @GetMapping("/tipo/{tipo}")
    public List<ProdutoDTO> buscarPorTipo(@PathVariable TipoProduto tipo) {
        return produtoService.buscarPorTipo(tipo);
    }
}
