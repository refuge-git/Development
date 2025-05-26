package school.sptech.refuge.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.repository.FuncionarioRepository;
import school.sptech.refuge.service.FuncionarioService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 funcionários, deve retornar corretamente")
    void listarQuandoAcionadoComFuncionariosCadastradasDeveRetornarCorretamenteTeste() {
        List<Funcionario> funcionarios = List.of(
                new Funcionario(1, "Rosa dos Santos", "12345678912", "(11)991234567", "rosa@gmail.com", "senha1234"),
                new Funcionario(2, "Mario Silva", "98765432198", "(11)998765678", "mario@gmail.com", "123senha"),
                new Funcionario(3, "Valeria Gomes", "32456712376", "(11)991235567", "valeria@gmail.com", "s123enha")

        );
        when(funcionarioRepository.findAll()).thenReturn(funcionarios);
        List<Funcionario> listaFuncionarios = funcionarioService.listar();
        assertEquals(3, listaFuncionarios.size());

    }

    @Test
    @DisplayName("Listar quando acionado e não houver funcionarios, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDevRetornarUmaColecaoVaziaTeste() {

        when(funcionarioRepository.findAll()).thenReturn(Collections.emptyList());
        List<Funcionario> resultado = funcionarioService.listar();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar funcionário correspondente")
    void buscarPorIdComIdExistenteDeveRetornarFuncionarioTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        when(funcionarioRepository.findById(1)).thenReturn(Optional.of(funcionario));

        Funcionario resultado = funcionarioService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar funcionário com ID inexistente")
    void buscarPorIdComIdInexistenteDeveLancarExcecaoTeste() {
        when(funcionarioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(FuncionarioNaoEncontradaException.class, () -> funcionarioService.buscarPorId(99));
    }

    @Test
    @DisplayName("Quando remover for acionado deve remover funcionário existente com sucesso")
    void removerFuncionarioExistenteDeveRemoverSemErrosTeste() {
        when(funcionarioRepository.existsById(1)).thenReturn(true);

        funcionarioService.removerPorId(1);

        verify(funcionarioRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção EntidadeNaoEncontradaException ao tentar remover funcionário inexistente")
    void removerFuncionarioInexistenteDeveLancarExcecaoEntidadeNaoEncontradaExceptionTeste() {
        when(funcionarioRepository.existsById(99)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.removerPorId(99));
    }

    @Test
    @DisplayName("Deve atualizar funcionário existente com sucesso")
    void atualizarFuncionarioExistenteComSucessoTeste() {
        Funcionario funcionario = new Funcionario(1, "Ana", "11111111111", "11988888888", "ana@email.com", "senha123");

        when(funcionarioRepository.existsById(funcionario.getId())).thenReturn(true);
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        Funcionario atualizado = funcionarioService.atualizar(funcionario);

        assertNotNull(atualizado);
        assertEquals("Ana", atualizado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção EntidadeNaoEncontradaException ao tentar atualizar funcionário inexistente")
    void atualizarFuncionarioInexistenteDeveRetornarEntidadeNaoEncontradaExceptionTeste() {
        Funcionario funcionario = new Funcionario(42, "Carlos", "00000000000", "11900000000", "carlos@email.com", "senha");

        when(funcionarioRepository.existsById(funcionario.getId())).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.atualizar(funcionario));
    }

}