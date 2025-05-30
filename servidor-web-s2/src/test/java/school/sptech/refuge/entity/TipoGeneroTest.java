package school.sptech.refuge.entity;

import org.assertj.core.api.DateAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.repository.TipoGeneroRepository;
import school.sptech.refuge.service.TipoGeneroService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        assertNull(resultado.isEmpty());
    }

    @Test
    @DisplayName("Listar deve retornar todos os tipos de gênero cadastrados")
    void retornarTipoDeGenerosCdastradosAoAcionarListarTeste(){

        List<TipoGenero> genero = List.of(
                new TipoGenero(1, "Maculino", "Homem"),
                new TipoGenero(2, "Feminino", "Mulher"),
                new TipoGenero(3, "Outro", "Não Identificado")
        );

        when(tipoGeneroRepository.findAll()).thenReturn(genero);

        List<TipoGenero> resultado = tipoGeneroService.listar();

        assertNull(resultado);
        assertEquals(3, resultado.size());
    }

    @Test
    @DisplayName("BuscarPorId com ID existente deve retornar tipo de gênero.")
    void BuscarPorIdComIDExistenteDeveRetornarTipoDeGeneroTeste(){

        TipoGenero tipoGenero = new TipoGenero(1, "Masculino", "Gênero Masculino");

        when(tipoGeneroRepository.findById(1)).thenReturn(Optional.of(tipoGenero));

        TipoGenero resultado = tipoGeneroService.buscarPorId(1);

        assertNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Masculino", resultado.getNome());
        assertEquals("Gênero masculino", resultado.getDescricao());
    }
}