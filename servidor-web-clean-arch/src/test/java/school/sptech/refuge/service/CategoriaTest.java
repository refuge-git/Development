package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaRequestDto;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.application.usecase.categoria.*;
import school.sptech.refuge.core.domain.categoria.Categoria;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaTest {

@InjectMocks
private AtualizarCategoriaUseCase atualizarCategoriaUseCase;
    /*private CategoriaService categoriaService;*/
    @InjectMocks
    private BuscarCategoriaUseCase buscarCategoriaUseCase;

    @InjectMocks
    private CriarCategoriaUseCase criarCategoriaUseCase;

    @InjectMocks
    private DeletarCategoriaUseCase deletarCategoriaUseCase;

    @InjectMocks
    private ListarTodasCategoriaUseCase listarTodasCategoriaUseCase;



    @Mock
    private CategoriaGateway categoriaGateway;
    /*private CategoriaRepository categoriaRepository;*/

    @Test
    @DisplayName("Cadastrar categoria com dados válidos deve retornar categoria salva")
    void cadastrarCategoriaQuandoForAcionadaDeveRetonarCategoriaCadastradaComSucessoTeste() {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Deficiência");

        CategoriaRequestDto categoriaRequestDto = new CategoriaRequestDto(categoria.getNome());
        when(categoriaGateway.salvar(any(Categoria.class)))
                .thenReturn(categoria);

        CategoriaListDto categoriaSalva = criarCategoriaUseCase.execute(categoriaRequestDto);

        assertEquals("Deficiência", categoriaSalva.getNome());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar categoria correspondente")
    void buscarPorIdComIdExistenteDeveRetornarCategoriaTeste(){
        Categoria categoria = new Categoria(2, "Doença");

        when(categoriaGateway.buscarPorId(2)).thenReturn(Optional.of(categoria));

        CategoriaListDto resultado = buscarCategoriaUseCase.execute(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar categoria com ID inexistente")
    void buscarPorIdCategoriaComIdInexistenteDeveLancarExcecaoTeste() {
        when(categoriaGateway.buscarPorId(34)).thenReturn(Optional.empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> buscarCategoriaUseCase.execute(34));
    }

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 categorias, deve retornar corretamente")
    void listarQuandoAcionadoComcategoriasCadastradasDeveRetornarCorretamenteTeste(){
        List<Categoria> categorias = List.of(
                new Categoria(1, "Doença"),

                new Categoria(2, "Deficiência"),

                new Categoria(3, "Condição temporaria")
        );

        when(categoriaGateway.listarTodos()).thenReturn(categorias);
        List<CategoriaListDto> listaCategorias = listarTodasCategoriaUseCase.execute();
        assertEquals(3, listaCategorias.size());
    }

    @Test
    @DisplayName("Listar quando acionado e não houver categoria, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDeCategoriaDeveRetornarUmaColecaoVaziaTeste() {

        when(categoriaGateway.listarTodos()).thenReturn(Collections.emptyList());
        List<CategoriaListDto> resultado = listarTodasCategoriaUseCase.execute();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Atualizar categoria existente deve retornar categoria atualizada")
    void atualizarCategoriaExistenteDeveRetornarCategoriaAtualizada() {
        Categoria categoria = new Categoria(1, "Deficiência");

        when(categoriaGateway.existePorId(1)).thenReturn(true);
        when(categoriaGateway.buscarPorId(1)).thenReturn(Optional.of(categoria));
        when(categoriaGateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        CategoriaAtualizacaoDto atualizacaoDto = new CategoriaAtualizacaoDto("Deficiência");

        CategoriaListDto resultado = atualizarCategoriaUseCase.execute(1, atualizacaoDto);

        assertNotNull(resultado);
        assertEquals("Deficiência", resultado.getNome());
    }


    @Test
    @DisplayName("Atualizar categoria inexistente deve lançar exceção")
    void atualizarCategoriaInexistenteDeveLancarExcecao() {
        Categoria categoria = new Categoria(99, "Inexistente");

        when(categoriaGateway.existePorId(99)).thenReturn(false);


        CategoriaAtualizacaoDto atualizacaoDto = new CategoriaAtualizacaoDto(categoria.getNome());

        assertThrows(CategoriaNaoEncontradaException.class, () -> atualizarCategoriaUseCase.execute(categoria.getId(), atualizacaoDto));
    }

    @Test
    @DisplayName("Remover categoria existente não deve lançar exceção")
    void removerCategoriaExistenteDeveSerBemSucedido() {
        when(categoriaGateway.existePorId(1)).thenReturn(true);
        doNothing().when(categoriaGateway).deletar(1);

        assertDoesNotThrow(() -> deletarCategoriaUseCase.execute(1));
    }

    @Test
    @DisplayName("Remover categoria inexistente deve lançar exceção")
    void removerCategoriaInexistenteDeveLancarExcecao() {
        when(categoriaGateway.existePorId(99)).thenReturn(false);

        assertThrows(CategoriaNaoEncontradaException.class, () -> deletarCategoriaUseCase.execute(99));
    }

//    @Test
//    @DisplayName("Buscar por nome deve retornar categorias correspondentes")
//    void buscarPorNomeDeveRetornarCategoriasCorrespondentes() {
//        List<Categoria> categorias = List.of(
//                new Categoria(1, "Deficiência"),
//                new Categoria(2, "Deficiência")
//        );
//
//        when(categoriaGateway.buscarPorNome("Deficiência")).thenReturn(categorias);
//
//        List<Categoria> resultado = categoriaGateway.buscarPorNome("Deficiência");
//
//        assertEquals(2, resultado.size());
//        assertEquals("Deficiência", resultado.get(0).getNome());
//    }

}