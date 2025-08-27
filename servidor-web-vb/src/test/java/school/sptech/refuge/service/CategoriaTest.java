package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.application.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.infrastructure.repository.CategoriaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaTest {

@InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Cadastrar categoria com dados válidos deve retornar categoria salva")
    void cadastrarCategoriaQuandoForAcionadaDeveRetonarCategoriaCadastradaComSucessoTeste() {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Deficiência");

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria categoriaSalva = categoriaService.cadastrar(categoria);

        assertEquals("Deficiência", categoriaSalva.getNome());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar categoria correspondente")
    void buscarPorIdComIdExistenteDeveRetornarCategoriaTeste(){
        Categoria categoria = new Categoria(2, "Doença");

        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoria));

        Categoria resultado = categoriaService.buscarPorId(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar categoria com ID inexistente")
    void buscarPorIdCategoriaComIdInexistenteDeveLancarExcecaoTeste() {
        when(categoriaRepository.findById(34)).thenReturn(Optional.empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.buscarPorId(34));
    }

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 categorias, deve retornar corretamente")
    void listarQuandoAcionadoComcategoriasCadastradasDeveRetornarCorretamenteTeste(){
        List<Categoria> categorias = List.of(
                new Categoria(1, "Doença"),

                new Categoria(2, "Deficiência"),

                new Categoria(3, "Condição temporaria")
        );

        when(categoriaRepository.findAll()).thenReturn(categorias);
        List<Categoria> listaCategorias = categoriaService.listar();
        assertEquals(3, listaCategorias.size());
    }

    @Test
    @DisplayName("Listar quando acionado e não houver categoria, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDeCategoriaDeveRetornarUmaColecaoVaziaTeste() {

        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());
        List<Categoria> resultado = categoriaService.listar();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Atualizar categoria existente deve retornar categoria atualizada")
    void atualizarCategoriaExistenteDeveRetornarCategoriaAtualizada() {
        Categoria categoria = new Categoria(1, "Deficiência");

        when(categoriaRepository.existsById(1)).thenReturn(true);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria resultado = categoriaService.atualizar(categoria);

        assertNotNull(resultado);
        assertEquals("Deficiência", resultado.getNome());
    }

    @Test
    @DisplayName("Atualizar categoria inexistente deve lançar exceção")
    void atualizarCategoriaInexistenteDeveLancarExcecao() {
        Categoria categoria = new Categoria(99, "Inexistente");

        when(categoriaRepository.existsById(99)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> categoriaService.atualizar(categoria));
    }

    @Test
    @DisplayName("Remover categoria existente não deve lançar exceção")
    void removerCategoriaExistenteDeveSerBemSucedido() {
        when(categoriaRepository.existsById(1)).thenReturn(true);
        doNothing().when(categoriaRepository).deleteById(1);

        assertDoesNotThrow(() -> categoriaService.remover(1));
    }

    @Test
    @DisplayName("Remover categoria inexistente deve lançar exceção")
    void removerCategoriaInexistenteDeveLancarExcecao() {
        when(categoriaRepository.existsById(99)).thenReturn(false);

        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.remover(99));
    }

    @Test
    @DisplayName("Buscar por nome deve retornar categorias correspondentes")
    void buscarPorNomeDeveRetornarCategoriasCorrespondentes() {
        List<Categoria> categorias = List.of(
                new Categoria(1, "Deficiência"),
                new Categoria(2, "Deficiência")
        );

        when(categoriaRepository.findAllByNome("Deficiência")).thenReturn(categorias);

        List<Categoria> resultado = categoriaService.buscarPorNome("Deficiência");

        assertEquals(2, resultado.size());
        assertEquals("Deficiência", resultado.get(0).getNome());
    }

}