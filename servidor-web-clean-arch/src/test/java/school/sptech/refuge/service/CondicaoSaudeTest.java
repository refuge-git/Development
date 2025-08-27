package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.antes.entity.Beneficiario;
import school.sptech.refuge.antes.entity.Categoria;
import school.sptech.refuge.antes.entity.CondicaoSaude;
import school.sptech.refuge.antes.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.antes.repository.BeneficiarioRepository;
import school.sptech.refuge.antes.repository.CategoriaRepository;
import school.sptech.refuge.antes.repository.CondicaoSaudeRepository;
import school.sptech.refuge.antes.service.CondicaoSaudeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CondicaoSaudeTest {

    @InjectMocks
    private CondicaoSaudeService condicaoSaudeService;

    @Mock
    private CondicaoSaudeRepository condicaoSaudeRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @Test
    @DisplayName("Deve cadastrar condição de saúde com sucesso")
    void deveCadastrarCondicaoSaudeComSucesso() {
        Categoria categoria = new Categoria();
        categoria.setId(1);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1);

        CondicaoSaude condicao = new CondicaoSaude(
                1,
                "Dor de cabeça",
                LocalDate.now(),
                "Paracetamol",
                "Paciente sente dor leve",
                beneficiario,
                categoria
        );

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(beneficiarioRepository.findById(1)).thenReturn(Optional.of(beneficiario));
        when(condicaoSaudeRepository.save(any())).thenReturn(condicao);

        CondicaoSaude resultado = condicaoSaudeService.cadastrar(condicao);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());

        verify(categoriaRepository, times(1)).findById(1);
        verify(beneficiarioRepository, times(1)).findById(1);
        verify(condicaoSaudeRepository, times(1)).save(condicao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com categoria inexistente")
    void deveLancarExcecaoAoCadastrarSemCategoriaValida() {
        CondicaoSaude condicao = new CondicaoSaude(null, "Dor", LocalDate.now(), "Analgésico", "Teste", null, new Categoria(99, "Invalida"));

        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> condicaoSaudeService.cadastrar(condicao));

        assertEquals("Categoria da condição não encontrada", excecao.getMessage());
        verify(categoriaRepository).findById(99);
        verify(condicaoSaudeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve listar todas as condições de saúde")
    void deveListarTodasCondicoes() {
        List<CondicaoSaude> condicoes = List.of(new CondicaoSaude(), new CondicaoSaude());
        when(condicaoSaudeRepository.findAll()).thenReturn(condicoes);

        List<CondicaoSaude> resultado = condicaoSaudeService.listar();

        assertEquals(2, resultado.size());
        verify(condicaoSaudeRepository).findAll();
    }

    @Test
    @DisplayName("Deve atualizar condição existente")
    void deveAtualizarCondicaoSaudeComSucesso() {
        CondicaoSaude condicao = new CondicaoSaude(1, "Febre", LocalDate.now(), "Repouso", "Paciente com febre", null, null);

        when(condicaoSaudeRepository.existsById(1)).thenReturn(true);
        when(condicaoSaudeRepository.save(condicao)).thenReturn(condicao);

        CondicaoSaude resultado = condicaoSaudeService.atualizar(condicao);

        assertNotNull(resultado);
        assertEquals("Febre", resultado.getDescricao());
        verify(condicaoSaudeRepository).existsById(1);
        verify(condicaoSaudeRepository).save(condicao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar condição inexistente")
    void deveLancarExcecaoAoAtualizarCondicaoInexistente() {
        CondicaoSaude condicao = new CondicaoSaude(99, "Inexistente", LocalDate.now(), "N/A", "N/A", null, null);

        when(condicaoSaudeRepository.existsById(99)).thenReturn(false);

        EntidadeNaoEncontradaException excecao = assertThrows(EntidadeNaoEncontradaException.class, () -> condicaoSaudeService.atualizar(condicao));

        assertEquals("Condição de saude com id 99 não encontrada", excecao.getMessage());
        verify(condicaoSaudeRepository).existsById(99);
        verify(condicaoSaudeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve remover condição de saúde existente")
    void deveRemoverCondicaoSaude() {
        when(condicaoSaudeRepository.existsById(1)).thenReturn(true);

        condicaoSaudeService.removerPorId(1);

        verify(condicaoSaudeRepository).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover condição inexistente")
    void deveLancarExcecaoAoRemoverCondicaoInexistente() {
        when(condicaoSaudeRepository.existsById(99)).thenReturn(false);

        EntidadeNaoEncontradaException excecao = assertThrows(EntidadeNaoEncontradaException.class, () -> condicaoSaudeService.removerPorId(99));

        assertEquals("Condição de saude com id 99 não encontrada", excecao.getMessage());
        verify(condicaoSaudeRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Deve listar condições por descrição contendo texto")
    void deveListarPorDescricao() {
        when(condicaoSaudeRepository.findByDescricaoContainingIgnoreCase("febre"))
                .thenReturn(List.of(new CondicaoSaude(), new CondicaoSaude()));

        List<CondicaoSaude> resultado = condicaoSaudeService.listarPorDescricao("febre");

        assertEquals(2, resultado.size());
        verify(condicaoSaudeRepository).findByDescricaoContainingIgnoreCase("febre");
    }

    @Test
    @DisplayName("Deve buscar condição por ID com sucesso")
    void deveBuscarPorIdComSucesso() {
        CondicaoSaude condicao = new CondicaoSaude(1, "Teste", LocalDate.now(), "Tratamento", "Obs", null, null);

        when(condicaoSaudeRepository.findById(1)).thenReturn(Optional.of(condicao));

        CondicaoSaude resultado = condicaoSaudeService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(condicaoSaudeRepository).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar por ID inexistente")
    void deveLancarExcecaoAoBuscarPorIdInexistente() {
        when(condicaoSaudeRepository.findById(999)).thenReturn(Optional.empty());

        CondicaoSaudeNaoEncontradaException excecao = assertThrows(CondicaoSaudeNaoEncontradaException.class, () -> condicaoSaudeService.buscarPorId(999));

        assertEquals("Condição de saúde de id 999 não encontrado", excecao.getMessage());
        verify(condicaoSaudeRepository).findById(999);
    }
}