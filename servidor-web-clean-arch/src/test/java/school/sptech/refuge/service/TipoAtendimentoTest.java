package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.application.exception.ViolacaoDeDadosException;
import school.sptech.refuge.core.application.usecase.funcionario.BuscarFuncionarioUseCase;
import school.sptech.refuge.core.application.usecase.tipoAtendimento.*;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoAtendimentoTest {

    @Mock
    private TipoAtendimentoGateway tipoAtendimentoGateway;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @InjectMocks
//    private TipoAtendimentoService service;
    private AtualizarTipoAtendimentoUseCase atualizarTipoAtendimentoUseCase;
    @InjectMocks
    private BuscarAtividadesRealizadasHojeUseCase buscarAtividadesRealizadasHojeUseCase;
    @InjectMocks
    private CriarTipoAtendimentoUseCase criarTipoAtendimentoUseCase;
    @InjectMocks
    private DeletarTipoAtendimentoUseCase deletarTipoAtendimentoUseCase;
    @InjectMocks
    private ListarTodosTipoAtendimentoUseCase listarTodosTipoAtendimentoUseCase;
    @InjectMocks
    private BuscarFuncionarioUseCase buscarFuncionarioUseCase;


    @Test
    @DisplayName("Deve criar tipo de atendimento com sucesso")
    void deveCriarTipoAtendimentoComSucesso() {
        Funcionario func = new Funcionario();
        func.setId(1);
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setFuncionario(func);

        when(funcionarioGateway.buscarPorId(1)).thenReturn(Optional.of(func));
        when(tipoAtendimentoGateway.salvar(any())).thenReturn(tipo);

        TipoAtendimentoRequestDto dto = new TipoAtendimentoRequestDto();
        dto.setIdFuncionario(func.getId());

        TipoAtendimentoResponseDto resultado = criarTipoAtendimentoUseCase.execute(dto);

        assertEquals(tipo, resultado);
        verify(tipoAtendimentoGateway).salvar(tipo);
    }

    @Test
    @DisplayName("Deve lançar exceção ao validar funcionário inexistente")
    void deveLancarExcecaoFuncionarioNaoEncontrado() {
        when(funcionarioGateway.buscarPorId(99)).thenReturn(Optional.empty());

        assertThrows(FuncionarioNaoEncontradaException.class, () -> buscarFuncionarioUseCase.execute(99));
    }

    @Test
    @DisplayName("Deve listar todos os tipos de atendimento")
    void deveListarTodos() {
        List<TipoAtendimento> lista = List.of(new TipoAtendimento(), new TipoAtendimento());
        when(tipoAtendimentoGateway.listar()).thenReturn(lista);

        List<TipoAtendimentoResponseDto> resultado = listarTodosTipoAtendimentoUseCase.execute();

        assertEquals(2, resultado.size());
    }

//    @Test
//    @DisplayName("Deve buscar tipo de atendimento por ID existente")
//    void deveBuscarPorIdExistente() {
//        TipoAtendimento tipo = new TipoAtendimento();
//        tipo.setId(1);
//        when(tipoAtendimentoGateway.buscarPorId(1)).thenReturn(Optional.of(tipo));
//
//        TipoAtendimento resultado = bu.buscarPorId(1);
//
//        assertEquals(tipo, resultado);
//    }

//    @Test
//    @DisplayName("Deve lançar exceção ao buscar tipo de atendimento por ID inexistente")
//    void deveLancarExcecaoBuscarIdInexistente() {
//        when(tipoAtendimentoGateway.findById(999)).thenReturn(Optional.empty());
//
//        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> service.buscarPorId(999));
//    }

    @Test
    @DisplayName("Deve atualizar tipo de atendimento existente")
    void deveAtualizarTipoAtendimento() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(1);
        when(tipoAtendimentoGateway.existePorId(1)).thenReturn(true);
        when(tipoAtendimentoGateway.salvar(tipo)).thenReturn(tipo);

        TipoAtendimentoRequestDto dto = new TipoAtendimentoRequestDto();

        TipoAtendimentoResponseDto resultado = atualizarTipoAtendimentoUseCase.execute(1,dto);

        assertEquals(tipo, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar tipo de atendimento inexistente")
    void deveLancarExcecaoAtualizarInexistente() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(999);
        when(tipoAtendimentoGateway.existePorId(999)).thenReturn(false);

        TipoAtendimentoRequestDto dto = new TipoAtendimentoRequestDto();

        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> atualizarTipoAtendimentoUseCase.execute(999, dto));
    }

    @Test
    @DisplayName("Deve deletar tipo de atendimento com sucesso")
    void deveDeletarTipoAtendimento() {
        when(tipoAtendimentoGateway.existePorId(1)).thenReturn(true);

        deletarTipoAtendimentoUseCase.execute(1);

        verify(tipoAtendimentoGateway, times(1)).deletar(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar tipo de atendimento inexistente")
    void deveLancarExcecaoAoDeletarTipoInexistente() {
        when(tipoAtendimentoGateway.existePorId(99)).thenReturn(false);

        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> deletarTipoAtendimentoUseCase.execute(99));
    }

    @Test
    @DisplayName("Deve lançar exceção de violação de dados ao deletar tipo com dependências")
    void deveLancarViolacaoDeDadosAoDeletar() {
        when(tipoAtendimentoGateway.existePorId(1)).thenReturn(true);
        doThrow(new org.springframework.dao.DataIntegrityViolationException("Erro")).when(tipoAtendimentoGateway).deletar(1);

        assertThrows(ViolacaoDeDadosException.class, () -> deletarTipoAtendimentoUseCase.execute(1));
    }
}
