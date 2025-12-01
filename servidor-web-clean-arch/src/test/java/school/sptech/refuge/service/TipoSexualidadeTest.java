package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeRequestDto;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.core.application.usecase.tiposexualidade.*;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoSexualidadeTest {

    @InjectMocks
//    private TipoSexualidadeService sexualidadeService;
    private AtualizarTipoSexualidadeUseCase atualizarTipoSexualidadeUseCase;
    @InjectMocks
    private BuscarTipoSexualidadePorIdUseCase buscarTipoSexualidadePorIdUseCase;
    @InjectMocks
    private CriarTipoSexualidadeUseCase criarTipoSexualidadeUseCase;
    @InjectMocks
    private DeletarTipoSexualidadeUseCase deletarTipoSexualidadeUseCase;
    @InjectMocks
    private ListarTodosTipoSexualidadeUseCase listarTodosTipoSexualidadeUseCase;



    @Mock
    private TipoSexualidadeGateway sexualidadeGateway;

    @Test
    @DisplayName("Deve cadastrar tipo de sexualdiade com sucesso!")
    void deveCadastrarTipoSexualidadeComSucesso(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(null, "Bissexual", "Atração por ambos os sexos");

        when(sexualidadeGateway.salvar(tipoSexualidade))
                .thenReturn(new TipoSexualidade(1, "Bissexual", "Atração por ambos os sexos"));

        TipoSexualidadeRequestDto dto = new TipoSexualidadeRequestDto("Bissexual", "Atração por ambos os sexos");

        TipoSexualidadeListDto resultado = criarTipoSexualidadeUseCase.execute(dto);

        assertNotNull(resultado);
        assertEquals("Bissexual", resultado.getNome());
        assertEquals("Atração por ambos os sexos", resultado.getDescricao());
        verify(sexualidadeGateway, times(1)).salvar(tipoSexualidade);
    }

    @Test
    @DisplayName("Não deve cadastrar tipo de sexualidade nulo")
    void naoDeveCadastrarTipoSexualidadeNulo(){

        when(sexualidadeGateway.salvar(null))
                .thenThrow(new IllegalArgumentException("TipoSexualidade não pode ser nulo"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> criarTipoSexualidadeUseCase.execute(null));

        assertEquals("TipoSexualidade não pode ser nulo", exception.getMessage());
        verify(sexualidadeGateway, times(1)).salvar(null);
    }

    @Test
    @DisplayName("Deve retornar tipo de sexualidade existente pelo ID")
    void deveRetornarTipoSexualidadeExistentePorId(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        when(sexualidadeGateway.buscarPorId(1))
                .thenReturn(Optional.of(tipoSexualidade));

        TipoSexualidadeListDto resultado = buscarTipoSexualidadePorIdUseCase.execute(1);

        assertNotNull(resultado);
        assertEquals("Heterossexual", resultado.getNome());
        assertEquals("Atração pelo sexo oposto", resultado.getDescricao());
        verify(sexualidadeGateway).buscarPorId(1);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo de sexualidade não for encontrado por ID")
    void deveLancarExcecaoQuandoTipoSexualidadeNaoEncontradoPorId(){

        int id = 10;

        when(sexualidadeGateway.buscarPorId(id))
                .thenReturn(Optional.empty());

        TipoSexualidadeNaoEncontradoException exception = assertThrows(
                TipoSexualidadeNaoEncontradoException.class, () -> buscarTipoSexualidadePorIdUseCase.execute(id));

        assertEquals("Tipo de sexualidade de id 10 não encontrado", exception.getMessage());
        verify(sexualidadeGateway).buscarPorId(id);
    }

    @Test
    @DisplayName("Deve retornar lista com tipos de sexualidade cadastrados")
    void deveRetornarListaDeTiposSexualidade(){

        List<TipoSexualidade> lista = List.of(
                new TipoSexualidade(1, "Gay", "Atração por pessoas do mesmo sexo"),
                new TipoSexualidade(2, "Bissexual", "Atração por ambos os sexos")
        );

        when(sexualidadeGateway.listarTodos()).thenReturn(lista);

        List<TipoSexualidadeListDto> resultado = listarTodosTipoSexualidadeUseCase.execute();

        assertEquals(2, resultado.size());
        verify(sexualidadeGateway).listarTodos();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver tipos de sexualidade")
    void deveRetornarListaVaziaQuandoNaoHouverDados(){

        when(sexualidadeGateway.listarTodos()).thenReturn(List.of());

        List<TipoSexualidadeListDto> resultado = listarTodosTipoSexualidadeUseCase.execute();

        assertTrue(resultado.isEmpty());
        verify(sexualidadeGateway).listarTodos();
    }
//
//    @Test
//    @DisplayName("Deve retornar lista com tipos de sexualidade que contenham a descrição informada")
//    void deveRetornarListaComBaseNaDescricao(){
//
//        String descricao = "atração";
//
//        List<TipoSexualidade> lista = List.of(
//                new TipoSexualidade(1, "Pansexual", "Atração por todos os gêneros")
//        );
//
//        when(sexualidadeGateway.findByDescricaoContainingIgnoreCase(descricao))
//                .thenReturn(lista);
//
//        List<TipoSexualidade> resultado = sexualidadeService.buscarPorDescricao(descricao);
//
//        assertEquals(1, resultado.size());
//        assertEquals("Pansexual", resultado.get(0).getNome());
//        verify(sexualidadeGateway).findByDescricaoContainingIgnoreCase(descricao);
//    }

    @Test
    @DisplayName("Deve atualizar tipo de sexualidade com sucesso")
    void deveAtualizarTipoSexualidadeComSucesso(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Lésbica", "Atração entre mulheres");

        when(sexualidadeGateway.existePorId(1)).thenReturn(true);
        when(sexualidadeGateway.salvar(tipoSexualidade)).thenReturn(tipoSexualidade);

        TipoSexualidadeRequestDto dto = new TipoSexualidadeRequestDto();
        dto.setNome(tipoSexualidade.getNome());
        dto.setDescricao(tipoSexualidade.getDescricao());

        TipoSexualidadeListDto atualizado = atualizarTipoSexualidadeUseCase.execute(1, dto);

        assertNotNull(atualizado);
        assertEquals("Lésbica", atualizado.getNome());
        verify(sexualidadeGateway).existePorId(1);
        verify(sexualidadeGateway).salvar(tipoSexualidade);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar tipo de sexualidade inexistente")
    void deveLancarExcecaoAoAtualizarTipoSexualidadeInexistente(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(99, "Outro", "Descrição");

        when(sexualidadeGateway.existePorId(99)).thenReturn(false);

        TipoSexualidadeRequestDto dto = new TipoSexualidadeRequestDto();
        dto.setNome(tipoSexualidade.getNome());
        dto.setDescricao(tipoSexualidade.getDescricao());

        TipoSexualidadeNaoEncontradoException ex = assertThrows(
                TipoSexualidadeNaoEncontradoException.class,
                () -> atualizarTipoSexualidadeUseCase.execute(99, dto));

        assertEquals("Tipo sexualidade de id 99 não encontrado", ex.getMessage());
        verify(sexualidadeGateway).existePorId(99);
    }

    @Test
    @DisplayName("Deve remover tipo de sexualidade existente")
    void deveRemoverTipoSexualidadeExistente(){

        when(sexualidadeGateway.existePorId(1)).thenReturn(true);
        doNothing().when(sexualidadeGateway).deletar(1);

        deletarTipoSexualidadeUseCase.execute(1);

        verify(sexualidadeGateway).existePorId(1);
        verify(sexualidadeGateway).deletar(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar remover tipo de sexualidade inexistente")
    void deveLancarExcecaoAoRemoverTipoSexualidadeInexistente(){

        when(sexualidadeGateway.existePorId(100)).thenReturn(false);

        TipoSexualidadeNaoEncontradoException exception = assertThrows(
                TipoSexualidadeNaoEncontradoException.class,
                () -> deletarTipoSexualidadeUseCase.execute(100));

        assertEquals("Tipo de sexualidade de id 100 não encontrado", exception.getMessage());
        verify(sexualidadeGateway).existePorId(100);
    }

}