package school.sptech.refuge.service;

// import org.assertj.core.api.DateAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.antes.entity.TipoGenero;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.antes.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.antes.repository.TipoGeneroRepository;
import school.sptech.refuge.antes.service.TipoGeneroService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoGeneroTest {

    @InjectMocks
    private TipoGeneroService tipoGeneroService;

    @Mock
    private TipoGeneroRepository tipoGeneroRepository;

    @Test
    @DisplayName("Listar deve retornar lista vazia quando não houver nenhum tipo de gênero")
    void retornarListaVaziaQuandoAcionarListarNaoHouverNenhumTipoDeGeneroTeste(){
        when(tipoGeneroRepository.findAll()).thenReturn(Collections.emptyList());

        List<TipoGenero> resultado = tipoGeneroService.listar();

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

        when(tipoGeneroRepository.findAll()).thenReturn(genero);

        List<TipoGenero> resultado = tipoGeneroService.listar();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
    }

    @Test
    @DisplayName("BuscarPorId com ID existente deve retornar tipo de gênero.")
    void buscarPorIdComIDExistenteDeveRetornarTipoDeGeneroTeste(){

        TipoGenero tipoGenero = new TipoGenero(1, "Masculino", "Gênero Masculino");

        when(tipoGeneroRepository.findById(1)).thenReturn(Optional.of(tipoGenero));

        TipoGenero resultado = tipoGeneroService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Masculino", resultado.getNome());
        assertEquals("Gênero Masculino", resultado.getDescricao());
    }

    @Test
    @DisplayName("BuscarPorId com ID inexistente deve lançar TipoGeneroNaoEncontradoException")
    void buscarPorIdComIdInexistenteDeveLancarExcecaoTipoGeneroTeste(){

        when(tipoGeneroRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(TipoGeneroNaoEncontradoException.class, () -> tipoGeneroService.buscarPorId(99));

    }

    @Test
    @DisplayName("RemoverPorId com ID existente deve remover sem erros")
    void RemoverPorIdComIDExistenteDeveRemoverSemErrosTeste(){
        when(tipoGeneroRepository.existsById(1)).thenReturn(true);

        tipoGeneroService.removerPorId(1);

        verify(tipoGeneroRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("RemoverPorId com ID inexistente deve lançar EntidadeNaoEncontradaException")
    void RemoverTipoGeneroPorIdComIDInexistenteDeveLancarEntidadeNaoEncontradaExceptionTeste(){
        when(tipoGeneroRepository.existsById(99)).thenReturn(false);

        assertThrows(TipoGeneroNaoEncontradoException.class, () -> tipoGeneroService.removerPorId(99));
    }

    @Test
    @DisplayName("Atualizar com tipo de gênero existente deve atualizar com sucesso")
    void AtualizarComTipoDeGeneroExistenteDeveComSucesso(){
        TipoGenero tipoGenero = new TipoGenero(3, "Masculino", "Gênero masculino");

        when(tipoGeneroRepository.existsById(tipoGenero.getId())).thenReturn(true);
        when(tipoGeneroRepository.save(tipoGenero)).thenReturn(tipoGenero);

        TipoGenero atualizado = tipoGeneroService.atualizar(tipoGenero);

        assertNotNull(atualizado);
        assertEquals(3, atualizado.getId());
        assertEquals("Masculino", atualizado.getNome());
        assertEquals("Gênero masculino", atualizado.getDescricao());
    }

    @Test
    @DisplayName("Atualizar com tipo de gênero inexistente deve lançar EntidadeNaoEncontradaException")
    void AtualizarComTipoDeGeneroInexistenteLancaEntidadeNaoEncontradaExceptionTeste(){

    TipoGenero tipoGenero = new TipoGenero(42, "Não-binário", "Gênero não identificado");

    when(tipoGeneroRepository.existsById(tipoGenero.getId())).thenReturn(false);

    assertThrows(EntidadeNaoEncontradaException.class, () -> tipoGeneroService.atualizar(tipoGenero));

    verify(tipoGeneroRepository, never()).save(any());
    }

    @Test
    @DisplayName("BuscarPorDescricao deve retornar tipos de gênero que contenham a descrição")
    void BuscarPorDescricaoDeveRetornarTiposDeGeneroQueContenhamADescricaoTeste(){

        List<TipoGenero> genero = List.of(
                new TipoGenero(2, "Feminino", "Gênero feminino"),
                new TipoGenero(4, "Não-binário", "Identidade não feminino")
        );


        when(tipoGeneroRepository.findByDescricaoContainingIgnoreCase("feminino")).thenReturn(genero);
        List<TipoGenero> resultado = tipoGeneroService.buscarPorDescricao("feminino");

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(g -> g.getDescricao().toLowerCase().contains("feminino")));
    }

    @Test
    @DisplayName("BuscarPorDescricao deve retornar lista vazia quando nenhuma descrição combinar")
    void BuscarPorDescricaoDeveRetornarListaVaziaQuandoNenhumaDescricaoCombinarTeste(){

        when(tipoGeneroRepository.findByDescricaoContainingIgnoreCase("alienígena")).thenReturn(Collections.emptyList());

        List<TipoGenero> resultado = tipoGeneroService.buscarPorDescricao("alienígena");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

    }

    @Test
    @DisplayName("Cadastrar deve retornar tipo de gênero salvo com sucesso")
    void cadastrarDeveRetornarTipoGeneroSalvoComSucessoTeste(){

        TipoGenero novoGenero = new TipoGenero(null, "Não-binário", "Gênero não-binário");
        TipoGenero salvo = new TipoGenero(1, "Não-binário", "Gênero não-binário");

        when(tipoGeneroRepository.save(novoGenero)).thenReturn(salvo);

        TipoGenero resultado = tipoGeneroService.cadastrar(novoGenero);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Não-binário", resultado.getNome());
        assertEquals("Gênero não-binário", resultado.getDescricao());
    }


}