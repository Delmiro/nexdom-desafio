package com.nexdom.estoque.br.service;

import com.nexdom.estoque.br.domain.entity.Produto;
import com.nexdom.estoque.br.domain.enums.TipoProduto;
import com.nexdom.estoque.br.dto.ProdutoDTO;
import com.nexdom.estoque.br.exception.ProdutoNaoEncontradoException;
import com.nexdom.estoque.br.mapper.ProdutoMapper;
import com.nexdom.estoque.br.repository.ProdutoRepository;
import com.nexdom.estoque.br.repository.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private ProdutoDTO criarProdutoDTO(Long id) {
        return new ProdutoDTO(id, "Produto Teste", TipoProduto.ELETRONICO, new BigDecimal("10.00"), 100);
    }

    @Test
    void deveSalvarERetornarProdutoDTO() {
        ProdutoDTO dto = criarProdutoDTO(null);
        Produto produto = ProdutoMapper.toEntity(dto);
        produto.setCodigo(1L);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        ProdutoDTO resultado = produtoService.salvar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.codigo());
        assertEquals(dto.descricao(), resultado.descricao());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

}
