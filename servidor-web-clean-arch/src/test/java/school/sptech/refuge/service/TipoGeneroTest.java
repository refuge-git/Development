package school.sptech.refuge.service;

// import org.assertj.core.api.DateAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.application.usecase.tipogenero.*;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoGeneroTest {

    @InjectMocks
//    private TipoGeneroService tipoGeneroService;
    private AtualizarTipoGeneroUseCase atualizarTipoGeneroUseCase;
    @InjectMocks
    private BuscarTipoGeneroPorIdUseCase buscarTipoGeneroPorIdUseCase;
    @InjectMocks
    private CriarTipoGeneroUseCase criarTipoGeneroUseCase;
    @InjectMocks
    private DeletarTipoGeneroUseCase deletarTipoGeneroUseCase;
    @InjectMocks
    private ListarTodosTipoGeneroUseCase listarTodosTipoGeneroUseCase;

    @Mock
    private TipoGeneroGateway tipoGeneroGateway;

    @Test
    @DisplayName("Listar deve retornar lista vazia quando não houver nenhum tipo de gênero")
    void retornarListaVaziaQuandoAcionarListarNaoHouverNenhumTipoDeGeneroTeste(){
        when(tipoGeneroGateway.listarTodos()).thenReturn(Collections.emptyList());

        List<TipoGeneroListDto> resultado = listarTodosTipoGeneroUseCase.execute();

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Listar deve retornar todos os tipos de gênero cadastrados")
    void retornarTipoDeGenerosCadastradosAoAcionarListarTeste(){

        List<TipoGenero> genero = List.of(
                new TipoGenero(1, "Masculino", "Gênero masculino"),
                new TipoGenero(2, "Feminino", "Gênero feminino"),
                new TipoGenero(3, "Outro", "Não Identificado")
        );

        when(tipoGeneroGateway.listarTodos()).thenReturn(genero);

        List<TipoGeneroListDto> resultado = listarTodosTipoGeneroUseCase.execute();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
    }

    @Test
    @DisplayName("BuscarPorId com ID existente deve retornar tipo de gênero.")
    void buscarPorIdComIDExistenteDeveRetornarTipoDeGeneroTeste(){

        TipoGenero tipoGenero = new TipoGenero(1, "Masculino", "Gênero Masculino");

        when(tipoGeneroGateway.buscarPorId(1)).thenReturn(Optional.of(tipoGenero));

        TipoGeneroListDto resultado = buscarTipoGeneroPorIdUseCase.execute(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Masculino", resultado.getNome());
        assertEquals("Gênero Masculino", resultado.getDescricao());
    }

    @Test
    @DisplayName("BuscarPorId com ID inexistente deve lançar TipoGeneroNaoEncontradoException")
    void buscarPorIdComIdInexistenteDeveLancarExcecaoTipoGeneroTeste(){

        when(tipoGeneroGateway.buscarPorId(99)).thenReturn(Optional.empty());

        assertThrows(TipoGeneroNaoEncontradoException.class, () -> buscarTipoGeneroPorIdUseCase.execute(99));

    }

    @Test
    @DisplayName("RemoverPorId com ID existente deve remover sem erros")
    void RemoverPorIdComIDExistenteDeveRemoverSemErrosTeste(){
        when(tipoGeneroGateway.existePorId(1)).thenReturn(true);

        deletarTipoGeneroUseCase.execute(1);

        verify(tipoGeneroGateway, times(1)).deletar(1);
    }

    @Test
    @DisplayName("RemoverPorId com ID inexistente deve lançar EntidadeNaoEncontradaException")
    void RemoverTipoGeneroPorIdComIDInexistenteDeveLancarEntidadeNaoEncontradaExceptionTeste(){
        when(tipoGeneroGateway.existePorId(99)).thenReturn(false);

        assertThrows(TipoGeneroNaoEncontradoException.class, () -> deletarTipoGeneroUseCase.execute(99));
    }

    @Test
    @DisplayName("Atualizar com tipo de gênero existente deve atualizar com sucesso")
    void AtualizarComTipoDeGeneroExistenteDeveComSucesso(){
        TipoGenero tipoGenero = new TipoGenero(3, "Masculino", "Gênero masculino");

        when(tipoGeneroGateway.existePorId(tipoGenero.getId())).thenReturn(true);
        when(tipoGeneroGateway.salvar(tipoGenero)).thenReturn(tipoGenero);

        TipoGeneroRequestDto dto = new TipoGeneroRequestDto();
        dto.setNome(tipoGenero.getNome());
        dto.setDescricao(tipoGenero.getDescricao());

        TipoGeneroListDto atualizado = atualizarTipoGeneroUseCase.execute(3, dto);

        assertNotNull(atualizado);
        assertEquals(3, atualizado.getId());
        assertEquals("Masculino", atualizado.getNome());
        assertEquals("Gênero masculino", atualizado.getDescricao());
    }

    @Test
    @DisplayName("Atualizar com tipo de gênero inexistente deve lançar EntidadeNaoEncontradaException")
    void AtualizarComTipoDeGeneroInexistenteLancaEntidadeNaoEncontradaExceptionTeste(){

    TipoGenero tipoGenero = new TipoGenero(42, "Não-binário", "Gênero não identificado");

        TipoGeneroRequestDto dto = new TipoGeneroRequestDto();
        dto.setNome(tipoGenero.getNome());
        dto.setDescricao(tipoGenero.getDescricao());

    when(tipoGeneroGateway.existePorId(tipoGenero.getId())).thenReturn(false);

    assertThrows(TipoGeneroNaoEncontradoException.class, () -> atualizarTipoGeneroUseCase.execute(42, dto));

    verify(tipoGeneroGateway, never()).salvar(any());
    }
//
//    @Test
//    @DisplayName("BuscarPorDescricao deve retornar tipos de gênero que contenham a descrição")
//    void BuscarPorDescricaoDeveRetornarTiposDeGeneroQueContenhamADescricaoTeste(){
//
//        List<TipoGenero> genero = List.of(
//                new TipoGenero(2, "Feminino", "Gênero feminino"),
//                new TipoGenero(4, "Não-binário", "Identidade não feminino")
//        );
//
//
//        when(tipoGeneroGateway.findByDescricaoContainingIgnoreCase("feminino")).thenReturn(genero);
//        List<TipoGenero> resultado = tipoGeneroService.buscarPorDescricao("feminino");
//
//        assertNotNull(resultado);
//        assertEquals(2, resultado.size());
//        assertTrue(resultado.stream().allMatch(g -> g.getDescricao().toLowerCase().contains("feminino")));
//    }

//    @Test
//    @DisplayName("BuscarPorDescricao deve retornar lista vazia quando nenhuma descrição combinar")
//    void BuscarPorDescricaoDeveRetornarListaVaziaQuandoNenhumaDescricaoCombinarTeste(){
//
//        when(tipoGeneroGateway.findByDescricaoContainingIgnoreCase("alienígena")).thenReturn(Collections.emptyList());
//
//        List<TipoGenero> resultado = tipoGeneroService.buscarPorDescricao("alienígena");
//
//        assertNotNull(resultado);
//        assertTrue(resultado.isEmpty());
//
//    }

    @Test
    @DisplayName("Cadastrar deve retornar tipo de gênero salvo com sucesso")
    void cadastrarDeveRetornarTipoGeneroSalvoComSucessoTeste(){

        TipoGenero novoGenero = new TipoGenero(null, "Não-binário", "Gênero não-binário");
        TipoGenero salvo = new TipoGenero(1, "Não-binário", "Gênero não-binário");

        TipoGeneroRequestDto dto = new TipoGeneroRequestDto();
        dto.setNome(novoGenero.getNome());
        dto.setDescricao(novoGenero.getDescricao());

        when(tipoGeneroGateway.salvar(novoGenero)).thenReturn(salvo);

        TipoGeneroListDto resultado = criarTipoGeneroUseCase.execute(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Não-binário", resultado.getNome());
        assertEquals("Gênero não-binário", resultado.getDescricao());
    }


}