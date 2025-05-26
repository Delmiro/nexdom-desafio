package com.nexdom.estoque.br.service;

import com.nexdom.estoque.br.domain.entity.Produto;
import com.nexdom.estoque.br.domain.enums.TipoProduto;
import com.nexdom.estoque.br.dto.ProdutoDTO;
import com.nexdom.estoque.br.mapper.ProdutoMapper;
import com.nexdom.estoque.br.repository.ProdutoRepository;
import com.nexdom.estoque.br.repository.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    private Produto criarProduto(Long id) {
        Produto produto = new Produto();
        produto.setCodigo(id);
        produto.setDescricao("Produto Teste");
        produto.setTipoProduto(TipoProduto.ELETRODOMESTICO);
        produto.setValorFornecedor(new BigDecimal("10.00"));
        produto.setQuantidadeEstoque(100);
        return produto;
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

    @Test
    void listarTodos_DeveRetornarListaDeProdutoDTO() {
        List<Produto> listaProdutos = List.of(criarProduto(1L), criarProduto(2L));

        when(produtoRepository.findAll()).thenReturn(listaProdutos);

        List<ProdutoDTO> resultado = produtoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).descricao());

        verify(produtoRepository, times(1)).findAll();
    }

}
