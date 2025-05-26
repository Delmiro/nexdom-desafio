package com.nexdom.estoque.br.mapper;

import com.nexdom.estoque.br.domain.entity.MovimentoEstoque;
import com.nexdom.estoque.br.domain.enums.TipoMovimentacao;
import com.nexdom.estoque.br.dto.MovimentoEstoqueDTO;

public class MovimentoEstoqueMapper {

    public static MovimentoEstoque toEntity(MovimentoEstoqueDTO dto) {
        if (dto == null) return null;
        MovimentoEstoque entity = new MovimentoEstoque();
        entity.setId(dto.getId());
        entity.setQuantidadeMovimentada(dto.getQuantidade());
        entity.setTipoMovimentacao(TipoMovimentacao.valueOf(dto.getTipoMovimento()));
        return entity;
    }

    public static MovimentoEstoqueDTO toDTO(MovimentoEstoque entity) {
        if (entity == null) return null;
        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setId(entity.getId());
        dto.setQuantidade(entity.getQuantidadeMovimentada());
        dto.setTipoMovimento(entity.getTipoMovimentacao().name());
        if (entity.getProduto() != null) {
            dto.setProdutoId(entity.getProduto().getCodigo());
            dto.setNomeProduto(entity.getProduto().getDescricao());
        }
        dto.setDataMovimento(entity.getDataVenda());
        return dto;
    }
}
