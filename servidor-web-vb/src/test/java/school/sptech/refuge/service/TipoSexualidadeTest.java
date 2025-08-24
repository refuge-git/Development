package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.entity.TipoSexualidade;
import school.sptech.refuge.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.repository.TipoSexualidadeRepository;
import school.sptech.refuge.service.TipoSexualidadeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoSexualidadeTest {

    @InjectMocks
    private TipoSexualidadeService sexualidadeService;

    @Mock
    private TipoSexualidadeRepository sexualidadeRepository;

    @Test
    @DisplayName("Deve cadastrar tipo de sexualdiade com sucesso!")
    void deveCadastrarTipoSexualidadeComSucesso(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(null, "Bissexual", "Atração por ambos os sexos");

        when(sexualidadeRepository.save(tipoSexualidade))
                .thenReturn(new TipoSexualidade(1, "Bissexual", "Atração por ambos os sexos"));

        TipoSexualidade resultado = sexualidadeService.cadastrar(tipoSexualidade);

        assertNotNull(resultado);
        assertEquals("Bissexual", resultado.getNome());
        assertEquals("Atração por ambos os sexos", resultado.getDescricao());
        verify(sexualidadeRepository, times(1)).save(tipoSexualidade);
    }

    @Test
    @DisplayName("Não deve cadastrar tipo de sexualidade nulo")
    void naoDeveCadastrarTipoSexualidadeNulo(){

        when(sexualidadeRepository.save(null))
                .thenThrow(new IllegalArgumentException("TipoSexualidade não pode ser nulo"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> sexualidadeService.cadastrar(null));

        assertEquals("TipoSexualidade não pode ser nulo", exception.getMessage());
        verify(sexualidadeRepository, times(1)).save(null);
    }

    @Test
    @DisplayName("Deve retornar tipo de sexualidade existente pelo ID")
    void deveRetornarTipoSexualidadeExistentePorId(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        when(sexualidadeRepository.findById(1))
                .thenReturn(Optional.of(tipoSexualidade));

        TipoSexualidade resultado = sexualidadeService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Heterossexual", resultado.getNome());
        assertEquals("Atração pelo sexo oposto", resultado.getDescricao());
        verify(sexualidadeRepository).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo de sexualidade não for encontrado por ID")
    void deveLancarExcecaoQuandoTipoSexualidadeNaoEncontradoPorId(){

        int id = 10;

        when(sexualidadeRepository.findById(id))
                .thenReturn(Optional.empty());

        TipoSexualidadeNaoEncontradoException exception = assertThrows(
                TipoSexualidadeNaoEncontradoException.class, () -> sexualidadeService.buscarPorId(id));

        assertEquals("Tipo de sexualidade de id 10 não encontrado", exception.getMessage());
        verify(sexualidadeRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar lista com tipos de sexualidade cadastrados")
    void deveRetornarListaDeTiposSexualidade(){

        List<TipoSexualidade> lista = List.of(
                new TipoSexualidade(1, "Gay", "Atração por pessoas do mesmo sexo"),
                new TipoSexualidade(2, "Bissexual", "Atração por ambos os sexos")
        );

        when(sexualidadeRepository.findAll()).thenReturn(lista);

        List<TipoSexualidade> resultado = sexualidadeService.listar();

        assertEquals(2, resultado.size());
        verify(sexualidadeRepository).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver tipos de sexualidade")
    void deveRetornarListaVaziaQuandoNaoHouverDados(){

        when(sexualidadeRepository.findAll()).thenReturn(List.of());

        List<TipoSexualidade> resultado = sexualidadeService.listar();

        assertTrue(resultado.isEmpty());
        verify(sexualidadeRepository).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com tipos de sexualidade que contenham a descrição informada")
    void deveRetornarListaComBaseNaDescricao(){

        String descricao = "atração";

        List<TipoSexualidade> lista = List.of(
                new TipoSexualidade(1, "Pansexual", "Atração por todos os gêneros")
        );

        when(sexualidadeRepository.findByDescricaoContainingIgnoreCase(descricao))
                .thenReturn(lista);

        List<TipoSexualidade> resultado = sexualidadeService.buscarPorDescricao(descricao);

        assertEquals(1, resultado.size());
        assertEquals("Pansexual", resultado.get(0).getNome());
        verify(sexualidadeRepository).findByDescricaoContainingIgnoreCase(descricao);
    }

    @Test
    @DisplayName("Deve atualizar tipo de sexualidade com sucesso")
    void deveAtualizarTipoSexualidadeComSucesso(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Lésbica", "Atração entre mulheres");

        when(sexualidadeRepository.existsById(1)).thenReturn(true);
        when(sexualidadeRepository.save(tipoSexualidade)).thenReturn(tipoSexualidade);

        TipoSexualidade atualizado = sexualidadeService.atualizar(tipoSexualidade);

        assertNotNull(atualizado);
        assertEquals("Lésbica", atualizado.getNome());
        verify(sexualidadeRepository).existsById(1);
        verify(sexualidadeRepository).save(tipoSexualidade);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar tipo de sexualidade inexistente")
    void deveLancarExcecaoAoAtualizarTipoSexualidadeInexistente(){

        TipoSexualidade tipoSexualidade = new TipoSexualidade(99, "Outro", "Descrição");

        when(sexualidadeRepository.existsById(99)).thenReturn(false);

        TipoSexualidadeNaoEncontradoException ex = assertThrows(
                TipoSexualidadeNaoEncontradoException.class,
                () -> sexualidadeService.atualizar(tipoSexualidade));

        assertEquals("Tipo sexualidade de id 99 não encontrado", ex.getMessage());
        verify(sexualidadeRepository).existsById(99);
    }

    @Test
    @DisplayName("Deve remover tipo de sexualidade existente")
    void deveRemoverTipoSexualidadeExistente(){

        when(sexualidadeRepository.existsById(1)).thenReturn(true);
        doNothing().when(sexualidadeRepository).deleteById(1);

        sexualidadeService.removerPorId(1);

        verify(sexualidadeRepository).existsById(1);
        verify(sexualidadeRepository).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar remover tipo de sexualidade inexistente")
    void deveLancarExcecaoAoRemoverTipoSexualidadeInexistente(){

        when(sexualidadeRepository.existsById(100)).thenReturn(false);

        TipoSexualidadeNaoEncontradoException exception = assertThrows(
                TipoSexualidadeNaoEncontradoException.class,
                () -> sexualidadeService.removerPorId(100));

        assertEquals("Tipo de sexualidade de id 100 não encontrado", exception.getMessage());
        verify(sexualidadeRepository).existsById(100);
    }

}