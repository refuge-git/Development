package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaRepository;
import school.sptech.refuge.antes.service.CategoriaService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaEntityTest {

@InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Cadastrar categoria com dados válidos deve retornar categoria salva")
    void cadastrarCategoriaQuandoForAcionadaDeveRetonarCategoriaCadastradaComSucessoTeste() {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(1);
        categoriaEntity.setNome("Deficiência");

        when(categoriaRepository.save(categoriaEntity)).thenReturn(categoriaEntity);

        CategoriaEntity categoriaEntitySalva = categoriaService.cadastrar(categoriaEntity);

        assertEquals("Deficiência", categoriaEntitySalva.getNome());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar categoria correspondente")
    void buscarPorIdComIdExistenteDeveRetornarCategoriaTeste(){
        CategoriaEntity categoriaEntity = new CategoriaEntity(2, "Doença");

        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoriaEntity));

        CategoriaEntity resultado = categoriaService.buscarPorId(2);

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
        List<CategoriaEntity> categoriaEntities = List.of(
                new CategoriaEntity(1, "Doença"),

                new CategoriaEntity(2, "Deficiência"),

                new CategoriaEntity(3, "Condição temporaria")
        );

        when(categoriaRepository.findAll()).thenReturn(categoriaEntities);
        List<CategoriaEntity> listaCategoriaEntities = categoriaService.listar();
        assertEquals(3, listaCategoriaEntities.size());
    }

    @Test
    @DisplayName("Listar quando acionado e não houver categoria, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDeCategoriaDeveRetornarUmaColecaoVaziaTeste() {

        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());
        List<CategoriaEntity> resultado = categoriaService.listar();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Atualizar categoria existente deve retornar categoria atualizada")
    void atualizarCategoriaExistenteDeveRetornarCategoriaAtualizada() {
        CategoriaEntity categoriaEntity = new CategoriaEntity(1, "Deficiência");

        when(categoriaRepository.existsById(1)).thenReturn(true);
        when(categoriaRepository.save(categoriaEntity)).thenReturn(categoriaEntity);

        CategoriaEntity resultado = categoriaService.atualizar(categoriaEntity);

        assertNotNull(resultado);
        assertEquals("Deficiência", resultado.getNome());
    }

    @Test
    @DisplayName("Atualizar categoria inexistente deve lançar exceção")
    void atualizarCategoriaInexistenteDeveLancarExcecao() {
        CategoriaEntity categoriaEntity = new CategoriaEntity(99, "Inexistente");

        when(categoriaRepository.existsById(99)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> categoriaService.atualizar(categoriaEntity));
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
        List<CategoriaEntity> categoriaEntities = List.of(
                new CategoriaEntity(1, "Deficiência"),
                new CategoriaEntity(2, "Deficiência")
        );

        when(categoriaRepository.findAllByNome("Deficiência")).thenReturn(categoriaEntities);

        List<CategoriaEntity> resultado = categoriaService.buscarPorNome("Deficiência");

        assertEquals(2, resultado.size());
        assertEquals("Deficiência", resultado.get(0).getNome());
    }

}