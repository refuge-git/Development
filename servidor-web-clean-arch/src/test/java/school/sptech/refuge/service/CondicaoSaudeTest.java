package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.support.DelegatePerTargetObjectIntroductionInterceptor;
import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeAtualizacaoDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.core.application.usecase.condicaosaude.*;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.categoria.Categoria;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CondicaoSaudeTest {

    @InjectMocks
    private AtualizarCondicaoSaudeUseCase atualizarCondicaoSaudeUseCase;
    @InjectMocks
    private BuscarCondicaoSaudeUseCase buscarCondicaoSaudeUseCase;
    @InjectMocks
    private CriarCondicaoSaudeUseCase criarCondicaoSaudeUseCase;
    @InjectMocks
    private DeletarCondicaoSaudeUseCase deletarCondicaoSaudeUseCase;
    @InjectMocks
    private ListagemCondicaoDeSaudeUseCase listagemCondicaoDeSaudeUseCase;
    @InjectMocks
    private ListarTodosCondicaoSaudeUseCase listarTodosCondicaoSaudeUseCase;

    @Mock
    private CondicaoSaudeGateway condicaoSaudeGateway;

    @Mock
    private CategoriaGateway categoriaGateway;

    @Mock
    private BeneficiarioGateway beneficiarioGateway;

    @Test
    @DisplayName("Deve cadastrar condição de saúde com sucesso")
    void deveCadastrarCondicaoSaudeComSucesso() {
        Categoria categoria = new Categoria();
        categoria.setId(1);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1);

//        public CondicaoSaude(Integer id, String diagnostico, String descricao, LocalDateTime dataRegistro, LocalDateTime dataAtualizacao, String tratamento, String observacoes, Beneficiario beneficiario, Categoria categoria) {
//            this.id = id;
//            this.diagnostico = diagnostico;
//            this.descricao = descricao;
//            this.dataRegistro = dataRegistro;
//            this.dataAtualizacao = dataAtualizacao;
//            this.tratamento = tratamento;
//            this.observacoes = observacoes;
//            this.beneficiario = beneficiario;
//            this.categoria = categoria;
//        }

        CondicaoSaude condicao = new CondicaoSaude(
                1,
                "Dor de cabeça",
                "Esta doente com dor de cabeça",
                LocalDateTime.of(2025,1,1,10,0),
                LocalDateTime.now(),
                "Paracetamol",
                "Paciente sente dor leve",
                beneficiario,
                categoria
        );

        when(categoriaGateway.buscarPorId(1)).thenReturn(Optional.of(categoria));
        when(beneficiarioGateway.buscarPorId(1)).thenReturn(Optional.of(beneficiario));
        when(condicaoSaudeGateway.salvar(any())).thenReturn(condicao);

        CondicaoSaudeRequestDto dto = new CondicaoSaudeRequestDto(
                "Dor de cabeça",
                "Esta doente com dor de cabeça",
                LocalDate.of(2025,1,1),
                "Paracetamol",
                "Paciente sente dor leve",
                beneficiario.getId(),
                categoria.getId()
        );

        CondicaoSaudeListDto resultado = criarCondicaoSaudeUseCase.execute(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());

        verify(categoriaGateway, times(1)).buscarPorId(1);
        verify(beneficiarioGateway, times(1)).buscarPorId(1);
        verify(condicaoSaudeGateway, times(1)).salvar(condicao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com categoria inexistente")
    void deveLancarExcecaoAoCadastrarSemCategoriaValida() {
        CondicaoSaude condicao = new CondicaoSaude(
                null,
                "Dor",
                "Dor de cabeça",
                LocalDateTime.of(2025,1,1,10,0),
                LocalDateTime.now(),
                "Analgésico",
                "Teste",
                null,
                new Categoria(99, "Invalida"));

        when(categoriaGateway.buscarPorId(99)).thenReturn(Optional.empty());

        CondicaoSaudeRequestDto dto = new CondicaoSaudeRequestDto(
                "Dor de cabeça",
                "Esta doente com dor de cabeça",
                LocalDate.of(2025,1,1),
                "Paracetamol",
                "Paciente sente dor leve",
                null,
                99
        );

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> criarCondicaoSaudeUseCase.execute(dto));

        assertEquals("Categoria da condição não encontrada", excecao.getMessage());
        verify(categoriaGateway).buscarPorId(99);
        verify(condicaoSaudeGateway, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve listar todas as condições de saúde")
    void deveListarTodasCondicoes() {
        List<CondicaoSaude> condicoes = List.of(new CondicaoSaude(), new CondicaoSaude());
        when(condicaoSaudeGateway.listarTodos()).thenReturn(condicoes);

        List<CondicaoSaudeListDto> resultado = listarTodosCondicaoSaudeUseCase.execute();

        assertEquals(2, resultado.size());
        verify(condicaoSaudeGateway).listarTodos();
    }

    @Test
    @DisplayName("Deve atualizar condição existente")
    void deveAtualizarCondicaoSaudeComSucesso() {

        CondicaoSaude condicao = new CondicaoSaude(1, "Febre","Muita febre",LocalDateTime.of(2025,1,1,10,0),
                LocalDateTime.now(), "Repouso", "Paciente com febre", null, null);

        when(condicaoSaudeGateway.existePorId(1)).thenReturn(true);
        when(condicaoSaudeGateway.salvar(condicao)).thenReturn(condicao);

        CondicaoSaudeAtualizacaoDto dto = new CondicaoSaudeAtualizacaoDto(
                "Febre",
                "Muita febre",
                "Repouso",
                "Paciente com febre",
                null,
                null
        );

        CondicaoSaudeListDto resultado = atualizarCondicaoSaudeUseCase.execute(1, dto);

        assertNotNull(resultado);
        assertEquals("Febre", resultado.getDescricao());
        verify(condicaoSaudeGateway).existePorId(1);
        verify(condicaoSaudeGateway).salvar(condicao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar condição inexistente")
    void deveLancarExcecaoAoAtualizarCondicaoInexistente() {
        CondicaoSaude condicao = new CondicaoSaude(99, "Inexistente", "Dor? Não!", LocalDateTime.of(2025,1,1,10,0),
                LocalDateTime.now(), "N/A", "N/A", null, null);

        CondicaoSaudeAtualizacaoDto dto = new CondicaoSaudeAtualizacaoDto(
                "Febre",
                "Muita febre",
                "Repouso",
                "Paciente com febre",
                null,
                null
        );

        when(condicaoSaudeGateway.existePorId(99)).thenReturn(false);

        CondicaoSaudeNaoEncontradaException excecao = assertThrows(CondicaoSaudeNaoEncontradaException.class, () -> atualizarCondicaoSaudeUseCase.execute(99, dto));

        assertEquals("Condição de saude com id 99 não encontrada", excecao.getMessage());
        verify(condicaoSaudeGateway).existePorId(99);
        verify(condicaoSaudeGateway, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve remover condição de saúde existente")
    void deveRemoverCondicaoSaude() {
        when(condicaoSaudeGateway.existePorId(1)).thenReturn(true);

        deletarCondicaoSaudeUseCase.execute(1);

        verify(condicaoSaudeGateway).deletar(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover condição inexistente")
    void deveLancarExcecaoAoRemoverCondicaoInexistente() {
        when(condicaoSaudeGateway.existePorId(99)).thenReturn(false);

        CondicaoSaudeNaoEncontradaException excecao = assertThrows(CondicaoSaudeNaoEncontradaException.class, () -> deletarCondicaoSaudeUseCase.execute(99));

        assertEquals("Condição de saude com id 99 não encontrada", excecao.getMessage());
        verify(condicaoSaudeGateway, never()).deletar(any());
    }
//
//    @Test
//    @DisplayName("Deve listar condições por descrição contendo texto")
//    void deveListarPorDescricao() {
//        when(condicaoSaudeGateway.findByDescricaoContainingIgnoreCase("febre"))
//                .thenReturn(List.of(new CondicaoSaude(), new CondicaoSaude()));
//
//        List<CondicaoSaude> resultado = li.listarPorDescricao("febre");
//
//        assertEquals(2, resultado.size());
//        verify(condicaoSaudeGateway).findByDescricaoContainingIgnoreCase("febre");
//    }

    @Test
    @DisplayName("Deve buscar condição por ID com sucesso")
    void deveBuscarPorIdComSucesso() {
        CondicaoSaude condicao = new CondicaoSaude(1, "Teste", "testando dores",LocalDateTime.of(2025,1,1,10,0),
                LocalDateTime.now(), "Tratamento", "Obs", null, null);

        when(condicaoSaudeGateway.buscarPorId(1)).thenReturn(Optional.of(condicao));

        CondicaoSaudeListDto resultado = buscarCondicaoSaudeUseCase.execute(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(condicaoSaudeGateway).buscarPorId(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar por ID inexistente")
    void deveLancarExcecaoAoBuscarPorIdInexistente() {
        when(condicaoSaudeGateway.buscarPorId(999)).thenReturn(Optional.empty());

        CondicaoSaudeNaoEncontradaException excecao = assertThrows(CondicaoSaudeNaoEncontradaException.class, () -> buscarCondicaoSaudeUseCase.execute(999));

        assertEquals("Condição de saúde de id 999 não encontrado", excecao.getMessage());
        verify(condicaoSaudeGateway).buscarPorId(999);
    }
}