package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.antes.entity.TipoAtendimento;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.antes.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaRepository;
import school.sptech.refuge.antes.repository.TipoAtendimentoRepository;
import school.sptech.refuge.antes.service.TipoAtendimentoService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoAtendimentoTest {

    @Mock
    private TipoAtendimentoRepository tipoRepo;

    @Mock
    private FuncionarioJpaRepository funcionarioRepo;

    @InjectMocks
    private TipoAtendimentoService service;

    @Test
    @DisplayName("Deve criar tipo de atendimento com sucesso")
    void deveCriarTipoAtendimentoComSucesso() {
        Funcionario func = new Funcionario();
        func.setId(1);
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setFuncionario(func);

        when(funcionarioRepo.findById(1)).thenReturn(Optional.of(func));
        when(tipoRepo.save(any())).thenReturn(tipo);

        TipoAtendimento resultado = service.criar(tipo);

        assertEquals(tipo, resultado);
        verify(tipoRepo).save(tipo);
    }

    @Test
    @DisplayName("Deve lançar exceção ao validar funcionário inexistente")
    void deveLancarExcecaoFuncionarioNaoEncontrado() {
        when(funcionarioRepo.findById(99)).thenReturn(Optional.empty());

        assertThrows(FuncionarioNaoEncontradaException.class, () -> service.validarFuncionario(99));
    }

    @Test
    @DisplayName("Deve listar todos os tipos de atendimento")
    void deveListarTodos() {
        List<TipoAtendimento> lista = List.of(new TipoAtendimento(), new TipoAtendimento());
        when(tipoRepo.findAll()).thenReturn(lista);

        List<TipoAtendimento> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Deve buscar tipo de atendimento por ID existente")
    void deveBuscarPorIdExistente() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(1);
        when(tipoRepo.findById(1)).thenReturn(Optional.of(tipo));

        TipoAtendimento resultado = service.buscarPorId(1);

        assertEquals(tipo, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar tipo de atendimento por ID inexistente")
    void deveLancarExcecaoBuscarIdInexistente() {
        when(tipoRepo.findById(999)).thenReturn(Optional.empty());

        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> service.buscarPorId(999));
    }

    @Test
    @DisplayName("Deve atualizar tipo de atendimento existente")
    void deveAtualizarTipoAtendimento() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(1);
        when(tipoRepo.existsById(1)).thenReturn(true);
        when(tipoRepo.save(tipo)).thenReturn(tipo);

        TipoAtendimento resultado = service.atualizar(tipo);

        assertEquals(tipo, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar tipo de atendimento inexistente")
    void deveLancarExcecaoAtualizarInexistente() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(999);
        when(tipoRepo.existsById(999)).thenReturn(false);

        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> service.atualizar(tipo));
    }

    @Test
    @DisplayName("Deve deletar tipo de atendimento com sucesso")
    void deveDeletarTipoAtendimento() {
        when(tipoRepo.existsById(1)).thenReturn(true);

        service.deletar(1);

        verify(tipoRepo, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar tipo de atendimento inexistente")
    void deveLancarExcecaoAoDeletarTipoInexistente() {
        when(tipoRepo.existsById(99)).thenReturn(false);

        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> service.deletar(99));
    }

    @Test
    @DisplayName("Deve lançar exceção de violação de dados ao deletar tipo com dependências")
    void deveLancarViolacaoDeDadosAoDeletar() {
        when(tipoRepo.existsById(1)).thenReturn(true);
        doThrow(new org.springframework.dao.DataIntegrityViolationException("Erro")).when(tipoRepo).deleteById(1);

        assertThrows(ViolacaoDeDadosException.class, () -> service.deletar(1));
    }
}
