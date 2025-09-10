package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeEntity;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaRepository;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeJpaRepository;
import school.sptech.refuge.antes.service.CondicaoSaudeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CondicaoSaudeEntityTest {

    @InjectMocks
    private CondicaoSaudeService condicaoSaudeService;

    @Mock
    private CondicaoSaudeJpaRepository condicaoSaudeJpaRepository;

    @Mock
    private CategoriaJpaRepository categoriaJpaRepository;

    @Mock
    private BeneficiarioJpaRepository beneficiarioJpaRepository;

    @Test
    @DisplayName("Deve cadastrar condição de saúde com sucesso")
    void deveCadastrarCondicaoSaudeComSucesso() {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(1);

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity();
        beneficiarioEntity.setId(1);

        CondicaoSaudeEntity condicao = new CondicaoSaudeEntity(
                1,
                "Dor de cabeça",
                LocalDate.now(),
                "Paracetamol",
                "Paciente sente dor leve",
                beneficiarioEntity,
                categoriaEntity
        );

        when(categoriaJpaRepository.findById(1)).thenReturn(Optional.of(categoriaEntity));
        when(beneficiarioJpaRepository.findById(1)).thenReturn(Optional.of(beneficiarioEntity));
        when(condicaoSaudeJpaRepository.save(any())).thenReturn(condicao);

        CondicaoSaudeEntity resultado = condicaoSaudeService.cadastrar(condicao);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());

        verify(categoriaJpaRepository, times(1)).findById(1);
        verify(beneficiarioJpaRepository, times(1)).findById(1);
        verify(condicaoSaudeJpaRepository, times(1)).save(condicao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com categoria inexistente")
    void deveLancarExcecaoAoCadastrarSemCategoriaValida() {
        CondicaoSaudeEntity condicao = new CondicaoSaudeEntity(null, "Dor", LocalDate.now(), "Analgésico", "Teste", null, new CategoriaJpaRepository(99, "Invalida") {
        });

        when(categoriaJpaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> condicaoSaudeService.cadastrar(condicao));

        assertEquals("Categoria da condição não encontrada", excecao.getMessage());
        verify(categoriaJpaRepository).findById(99);
        verify(condicaoSaudeJpaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve listar todas as condições de saúde")
    void deveListarTodasCondicoes() {
        List<CondicaoSaudeEntity> condicoes = List.of(new CondicaoSaudeEntity(1, "Dor de cabeça", LocalDate.now(), "Paracetamol", "Paciente sente dor leve", beneficiarioEntity, categoriaEntity), new CondicaoSaudeEntity(1, "Dor de cabeça", LocalDate.now(), "Paracetamol", "Paciente sente dor leve", beneficiarioEntity, categoriaEntity));
        when(condicaoSaudeJpaRepository.findAll()).thenReturn(condicoes);

        List<CondicaoSaudeEntity> resultado = condicaoSaudeService.listar();

        assertEquals(2, resultado.size());
        verify(condicaoSaudeJpaRepository).findAll();
    }

    @Test
    @DisplayName("Deve atualizar condição existente")
    void deveAtualizarCondicaoSaudeComSucesso() {
        CondicaoSaudeEntity condicao = new CondicaoSaudeEntity(1, "Febre", LocalDate.now(), "Repouso", "Paciente com febre", null, null);

        when(condicaoSaudeJpaRepository.existsById(1)).thenReturn(true);
        when(condicaoSaudeJpaRepository.save(condicao)).thenReturn(condicao);

        CondicaoSaudeEntity resultado = condicaoSaudeService.atualizar(condicao);

        assertNotNull(resultado);
        assertEquals("Febre", resultado.getDescricao());
        verify(condicaoSaudeJpaRepository).existsById(1);
        verify(condicaoSaudeJpaRepository).save(condicao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar condição inexistente")
    void deveLancarExcecaoAoAtualizarCondicaoInexistente() {
        CondicaoSaudeEntity condicao = new CondicaoSaudeEntity(99, "Inexistente", LocalDate.now(), "N/A", "N/A", null, null);

        when(condicaoSaudeJpaRepository.existsById(99)).thenReturn(false);

        EntidadeNaoEncontradaException excecao = assertThrows(EntidadeNaoEncontradaException.class, () -> condicaoSaudeService.atualizar(condicao));

        assertEquals("Condição de saude com id 99 não encontrada", excecao.getMessage());
        verify(condicaoSaudeJpaRepository).existsById(99);
        verify(condicaoSaudeJpaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve remover condição de saúde existente")
    void deveRemoverCondicaoSaude() {
        when(condicaoSaudeJpaRepository.existsById(1)).thenReturn(true);

        condicaoSaudeService.removerPorId(1);

        verify(condicaoSaudeJpaRepository).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover condição inexistente")
    void deveLancarExcecaoAoRemoverCondicaoInexistente() {
        when(condicaoSaudeJpaRepository.existsById(99)).thenReturn(false);

        EntidadeNaoEncontradaException excecao = assertThrows(EntidadeNaoEncontradaException.class, () -> condicaoSaudeService.removerPorId(99));

        assertEquals("Condição de saude com id 99 não encontrada", excecao.getMessage());
        verify(condicaoSaudeJpaRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Deve listar condições por descrição contendo texto")
    void deveListarPorDescricao() {
        when(condicaoSaudeJpaRepository.findByDescricaoContainingIgnoreCase("febre"))
                .thenReturn(List.of(new CondicaoSaudeEntity(1, "Dor de cabeça", LocalDate.now(), "Paracetamol", "Paciente sente dor leve", beneficiarioEntity, categoriaEntity), new CondicaoSaudeEntity(1, "Dor de cabeça", LocalDate.now(), "Paracetamol", "Paciente sente dor leve", beneficiarioEntity, categoriaEntity)));

        List<CondicaoSaudeEntity> resultado = condicaoSaudeService.listarPorDescricao("febre");

        assertEquals(2, resultado.size());
        verify(condicaoSaudeJpaRepository).findByDescricaoContainingIgnoreCase("febre");
    }

    @Test
    @DisplayName("Deve buscar condição por ID com sucesso")
    void deveBuscarPorIdComSucesso() {
        CondicaoSaudeEntity condicao = new CondicaoSaudeEntity(1, "Teste", LocalDate.now(), "Tratamento", "Obs", null, null);

        when(condicaoSaudeJpaRepository.findById(1)).thenReturn(Optional.of(condicao));

        CondicaoSaudeEntity resultado = condicaoSaudeService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(condicaoSaudeJpaRepository).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar por ID inexistente")
    void deveLancarExcecaoAoBuscarPorIdInexistente() {
        when(condicaoSaudeJpaRepository.findById(999)).thenReturn(Optional.empty());

        CondicaoSaudeNaoEncontradaException excecao = assertThrows(CondicaoSaudeNaoEncontradaException.class, () -> condicaoSaudeService.buscarPorId(999));

        assertEquals("Condição de saúde de id 999 não encontrado", excecao.getMessage());
        verify(condicaoSaudeJpaRepository).findById(999);
    }
}