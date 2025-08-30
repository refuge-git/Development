package school.sptech.refuge.service;

// import org.assertj.core.api.DateAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroJpaRepository;
import school.sptech.refuge.antes.service.TipoGeneroService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoGeneroEntityTest {

    @InjectMocks
    private TipoGeneroService tipoGeneroService;

    @Mock
    private TipoGeneroJpaRepository tipoGeneroJpaRepository;

    @Test
    @DisplayName("Listar deve retornar lista vazia quando não houver nenhum tipo de gênero")
    void retornarListaVaziaQuandoAcionarListarNaoHouverNenhumTipoDeGeneroTeste(){
        when(tipoGeneroJpaRepository.findAll()).thenReturn(Collections.emptyList());

        List<TipoGeneroEntity> resultado = tipoGeneroService.listar();

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Listar deve retornar todos os tipos de gênero cadastrados")
    void retornarTipoDeGenerosCadastradosAoAcionarListarTeste(){

        List<TipoGeneroEntity> genero = List.of(
                new TipoGeneroEntity(1, "Masculino", "Gênero masculino"),
                new TipoGeneroEntity(2, "Feminino", "Gênero feminino"),
                new TipoGeneroEntity(3, "Outro", "Não Identificado")
        );

        when(tipoGeneroJpaRepository.findAll()).thenReturn(genero);

        List<TipoGeneroEntity> resultado = tipoGeneroService.listar();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
    }

    @Test
    @DisplayName("BuscarPorId com ID existente deve retornar tipo de gênero.")
    void buscarPorIdComIDExistenteDeveRetornarTipoDeGeneroTeste(){

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Masculino", "Gênero Masculino");

        when(tipoGeneroJpaRepository.findById(1)).thenReturn(Optional.of(tipoGeneroEntity));

        TipoGeneroEntity resultado = tipoGeneroService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Masculino", resultado.getNome());
        assertEquals("Gênero Masculino", resultado.getDescricao());
    }

    @Test
    @DisplayName("BuscarPorId com ID inexistente deve lançar TipoGeneroNaoEncontradoException")
    void buscarPorIdComIdInexistenteDeveLancarExcecaoTipoGeneroTeste(){

        when(tipoGeneroJpaRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(TipoGeneroNaoEncontradoException.class, () -> tipoGeneroService.buscarPorId(99));

    }

    @Test
    @DisplayName("RemoverPorId com ID existente deve remover sem erros")
    void RemoverPorIdComIDExistenteDeveRemoverSemErrosTeste(){
        when(tipoGeneroJpaRepository.existsById(1)).thenReturn(true);

        tipoGeneroService.removerPorId(1);

        verify(tipoGeneroJpaRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("RemoverPorId com ID inexistente deve lançar EntidadeNaoEncontradaException")
    void RemoverTipoGeneroPorIdComIDInexistenteDeveLancarEntidadeNaoEncontradaExceptionTeste(){
        when(tipoGeneroJpaRepository.existsById(99)).thenReturn(false);

        assertThrows(TipoGeneroNaoEncontradoException.class, () -> tipoGeneroService.removerPorId(99));
    }

    @Test
    @DisplayName("Atualizar com tipo de gênero existente deve atualizar com sucesso")
    void AtualizarComTipoDeGeneroExistenteDeveComSucesso(){
        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(3, "Masculino", "Gênero masculino");

        when(tipoGeneroJpaRepository.existsById(tipoGeneroEntity.getId())).thenReturn(true);
        when(tipoGeneroJpaRepository.save(tipoGeneroEntity)).thenReturn(tipoGeneroEntity);

        TipoGeneroEntity atualizado = tipoGeneroService.atualizar(tipoGeneroEntity);

        assertNotNull(atualizado);
        assertEquals(3, atualizado.getId());
        assertEquals("Masculino", atualizado.getNome());
        assertEquals("Gênero masculino", atualizado.getDescricao());
    }

    @Test
    @DisplayName("Atualizar com tipo de gênero inexistente deve lançar EntidadeNaoEncontradaException")
    void AtualizarComTipoDeGeneroInexistenteLancaEntidadeNaoEncontradaExceptionTeste(){

    TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(42, "Não-binário", "Gênero não identificado");

    when(tipoGeneroJpaRepository.existsById(tipoGeneroEntity.getId())).thenReturn(false);

    assertThrows(EntidadeNaoEncontradaException.class, () -> tipoGeneroService.atualizar(tipoGeneroEntity));

    verify(tipoGeneroJpaRepository, never()).save(any());
    }

    @Test
    @DisplayName("BuscarPorDescricao deve retornar tipos de gênero que contenham a descrição")
    void BuscarPorDescricaoDeveRetornarTiposDeGeneroQueContenhamADescricaoTeste(){

        List<TipoGeneroEntity> genero = List.of(
                new TipoGeneroEntity(2, "Feminino", "Gênero feminino"),
                new TipoGeneroEntity(4, "Não-binário", "Identidade não feminino")
        );


        when(tipoGeneroJpaRepository.findByDescricaoContainingIgnoreCase("feminino")).thenReturn(genero);
        List<TipoGeneroEntity> resultado = tipoGeneroService.buscarPorDescricao("feminino");

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(g -> g.getDescricao().toLowerCase().contains("feminino")));
    }

    @Test
    @DisplayName("BuscarPorDescricao deve retornar lista vazia quando nenhuma descrição combinar")
    void BuscarPorDescricaoDeveRetornarListaVaziaQuandoNenhumaDescricaoCombinarTeste(){

        when(tipoGeneroJpaRepository.findByDescricaoContainingIgnoreCase("alienígena")).thenReturn(Collections.emptyList());

        List<TipoGeneroEntity> resultado = tipoGeneroService.buscarPorDescricao("alienígena");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

    }

    @Test
    @DisplayName("Cadastrar deve retornar tipo de gênero salvo com sucesso")
    void cadastrarDeveRetornarTipoGeneroSalvoComSucessoTeste(){

        TipoGeneroEntity novoGenero = new TipoGeneroEntity(null, "Não-binário", "Gênero não-binário");
        TipoGeneroEntity salvo = new TipoGeneroEntity(1, "Não-binário", "Gênero não-binário");

        when(tipoGeneroJpaRepository.save(novoGenero)).thenReturn(salvo);

        TipoGeneroEntity resultado = tipoGeneroService.cadastrar(novoGenero);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Não-binário", resultado.getNome());
        assertEquals("Gênero não-binário", resultado.getDescricao());
    }


}