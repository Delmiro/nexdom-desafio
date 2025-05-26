package com.nexdom.estoque.br.controller;

import com.nexdom.estoque.br.domain.enums.TipoMovimentacao;
import com.nexdom.estoque.br.dto.LucroProdutoDTO;
import com.nexdom.estoque.br.dto.MovimentoEstoqueDTO;
import com.nexdom.estoque.br.dto.ProdutoTipoResumoDTO;
import com.nexdom.estoque.br.exception.NegocioException;
import com.nexdom.estoque.br.service.MovimentoEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentos")
@RequiredArgsConstructor
public class MovimentoEstoqueController {

    private final MovimentoEstoqueService movimentoService;

    @Operation(
            summary = "Registrar um movimento de estoque (entrada ou saída)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movimento registrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida ou regra de negócio violada")
            }
    )
    @PostMapping
    public ResponseEntity<MovimentoEstoqueDTO> registrar(
            @RequestBody @Validated MovimentoEstoqueDTO dto
    ) {
        try {
            MovimentoEstoqueDTO salvo = movimentoService.registrarMovimento(dto);
            return ResponseEntity.ok(salvo);
        } catch (NegocioException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Consultar produtos por tipo de movimentação",
            description = "Retorna para cada produto o total de saídas e a quantidade disponível",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
            }
    )
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ProdutoTipoResumoDTO>> porTipo(
            @PathVariable TipoMovimentacao tipo
    ) {
        List<ProdutoTipoResumoDTO> resumo = movimentoService.consultarProdutosPorTipo(tipo.name());
        return ResponseEntity.ok(resumo);
    }

    @Operation(
            summary = "Consultar lucro por produto",
            description = "Para cada produto retorna a quantidade total de saída e o lucro total",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta de lucros realizada com sucesso")
            }
    )
    @GetMapping("/lucro")
    public ResponseEntity<List<LucroProdutoDTO>> lucroPorProduto() {
        List<LucroProdutoDTO> lucros = movimentoService.consultarLucroPorProduto();
        return ResponseEntity.ok(lucros);
    }
}
