package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioAtualizacaoDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioRequestDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioTokenDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.usecase.funcionario.*;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioTest {

    @InjectMocks
    private AtualizarFuncionarioUseCase atualizarFuncionarioUseCase;
    @InjectMocks
    private AutenticarFuncionarioUseCase autenticarFuncionarioUseCase;
    @InjectMocks
    private BuscarFuncionarioPorEmailUseCase buscarFuncionarioPorEmailUseCase;
    @InjectMocks
    private BuscarFuncionarioUseCase buscarFuncionarioUseCase;
    @InjectMocks
    private CriarFuncionarioUseCase criarFuncionarioUseCase;
    @InjectMocks
    private DeletarFuncionarioUseCase deletarFuncionarioUseCase;
    @InjectMocks
    private ListagemFuncionarioUseCase listagemFuncionarioUseCase;
    @InjectMocks
    private ListarTodosFuncionariosUseCase listarTodosFuncionariosUseCase;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private school.sptech.refuge.infrastructure.config.GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 funcionários, deve retornar corretamente")
    void listarQuandoAcionadoComFuncionariosCadastradasDeveRetornarCorretamenteTeste() {
        List<Funcionario> funcionarios = List.of(
                new Funcionario(1, "Rosa dos Santos", "12345678912", "(11)991234567", "rosa@gmail.com", "senha1234"),
                new Funcionario(2, "Mario Silva", "98765432198", "(11)998765678", "mario@gmail.com", "123senha"),
                new Funcionario(3, "Valeria Gomes", "32456712376", "(11)991235567", "valeria@gmail.com", "s123enha")

        );
        when(funcionarioGateway.listarTodos()).thenReturn(funcionarios);
        List<FuncionarioListDto> listaFuncionarios = listarTodosFuncionariosUseCase.execute();
        assertEquals(3, listaFuncionarios.size());

    }

    @Test
    @DisplayName("Listar quando acionado e não houver funcionarios, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDevRetornarUmaColecaoVaziaTeste() {

        when(funcionarioGateway.listarTodos()).thenReturn(Collections.emptyList());
        List<FuncionarioListDto> resultado = listarTodosFuncionariosUseCase.execute();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar funcionário correspondente")
    void buscarPorIdComIdExistenteDeveRetornarFuncionarioTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        when(funcionarioGateway.buscarPorId(1)).thenReturn(Optional.of(funcionario));

        FuncionarioListDto resultado = buscarFuncionarioUseCase.execute(1);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar funcionário com ID inexistente")
    void buscarPorIdComIdInexistenteDeveLancarExcecaoTeste() {
        when(funcionarioGateway.buscarPorId(99)).thenReturn(Optional.empty());

        assertThrows(FuncionarioNaoEncontradaException.class, () -> buscarFuncionarioUseCase.execute(99));
    }

    @Test
    @DisplayName("Quando remover for acionado deve remover funcionário existente com sucesso")
    void removerFuncionarioExistenteDeveRemoverSemErrosTeste() {
        when(funcionarioGateway.existePorId(1)).thenReturn(true);

        deletarFuncionarioUseCase.execute(1);

        verify(funcionarioGateway, times(1)).deletar(1);
    }

    @Test
    @DisplayName("Deve lançar exceção EntidadeNaoEncontradaException ao tentar remover funcionário inexistente")
    void removerFuncionarioInexistenteDeveLancarExcecaoEntidadeNaoEncontradaExceptionTeste() {
        when(funcionarioGateway.existePorId(99)).thenReturn(false);

        assertThrows(FuncionarioNaoEncontradaException.class, () -> deletarFuncionarioUseCase.execute(99));
    }

    @Test
    @DisplayName("Deve atualizar funcionário existente com sucesso")
    void atualizarFuncionarioExistenteComSucessoTeste() {
        Funcionario funcionario = new Funcionario(1, "Ana",
                "11111111111",
                "11988888888",
                "ana@email.com",
                "senha123");

        FuncionarioAtualizacaoDto atualizacaoDto = new FuncionarioAtualizacaoDto();
        atualizacaoDto.setNome(funcionario.getNome());
        atualizacaoDto.setCpf(funcionario.getCpf());
        atualizacaoDto.setTelefone(funcionario.getTelefone());
        atualizacaoDto.setEmail(funcionario.getEmail());
        atualizacaoDto.setSenha(funcionario.getSenha());

        when(funcionarioGateway.existePorId(funcionario.getId())).thenReturn(true);
        when(funcionarioGateway.salvar(funcionario)).thenReturn(funcionario);

        FuncionarioListDto atualizado = atualizarFuncionarioUseCase.execute(funcionario.getId(), atualizacaoDto);

        assertNotNull(atualizado);
        assertEquals("Ana", atualizado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção EntidadeNaoEncontradaException ao tentar atualizar funcionário inexistente")
    void atualizarFuncionarioInexistenteDeveRetornarEntidadeNaoEncontradaExceptionTeste() {
        Funcionario funcionario = new Funcionario(42, "Carlos", "00000000000", "11900000000", "carlos@email.com", "senha");

        when(funcionarioGateway.existePorId(funcionario.getId())).thenReturn(false);

        FuncionarioAtualizacaoDto atualizacaoDto = new FuncionarioAtualizacaoDto();
        atualizacaoDto.setNome(funcionario.getNome());
        atualizacaoDto.setCpf(funcionario.getCpf());
        atualizacaoDto.setTelefone(funcionario.getTelefone());
        atualizacaoDto.setEmail(funcionario.getEmail());
        atualizacaoDto.setSenha(funcionario.getSenha());


        assertThrows(FuncionarioNaoEncontradaException.class, () -> atualizarFuncionarioUseCase.execute(funcionario.getId(), atualizacaoDto));
    }

    @Test
    @DisplayName("listarTodos deve retornar lista de FuncionarioListDto corretamente")
    void listarTodosDeveRetornarListaDeFuncionarioListDtoTeste() {
        List<Funcionario> funcionarios = List.of(
                new Funcionario(1, "Rosa dos Santos", "93451123491", "111991234567", "rosa@gmail.com", "senha1234"),
                new Funcionario(2, "Mario Silva", "67898714354", "(11)998765678", "mario@gmail.com", "123senha")
        );

        when(funcionarioGateway.listarTodos()).thenReturn(funcionarios);
        List<FuncionarioListDto> resultado = listarTodosFuncionariosUseCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals("Rosa dos Santos", resultado.get(0).getNome());
        assertEquals("Mario Silva", resultado.get(1).getNome());

        verify(funcionarioGateway, times(1)).listarTodos();
    }

    @Test
    @DisplayName("listarTodos deve retornar lista vazia quando não houver funcionários cadastrados")
    void listarTodosDeveRetornarListaVaziaQuandoNaoHouverFuncionarios() {
        when(funcionarioGateway.listarTodos()).thenReturn(Collections.emptyList());

        List<FuncionarioListDto> resultado = listarTodosFuncionariosUseCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(funcionarioGateway, times(1)).listarTodos();
    }


    @Test
    @DisplayName("Criar funcionário deve criptografar senha e salvar com sucesso")
    void criarFuncionarioDeveSalvarComSenhaCriptografadaTeste() {

        Funcionario funcionario = new Funcionario(
                42,
                "Carlos",
                "00000000000",
                "11900000000",
                "carlos@email.com",
                "senha");
        when(passwordEncoder.encode("senha")).thenReturn("senhaCriptografada");

        FuncionarioRequestDto funcionarioRequestDto = new FuncionarioRequestDto();
        funcionarioRequestDto.setNome(funcionario.getNome());
        funcionarioRequestDto.setCpf(funcionario.getCpf());
        funcionarioRequestDto.setTelefone(funcionario.getTelefone());
        funcionarioRequestDto.setEmail(funcionario.getEmail());
        funcionarioRequestDto.setSenha(funcionario.getSenha());

        criarFuncionarioUseCase.execute(funcionarioRequestDto);


        verify(passwordEncoder).encode("senha");
        verify(funcionarioGateway).salvar(argThat(f -> f.getSenha().equals("senhaCriptografada")));
    }



    @Test
    @DisplayName("Autenticar funcionário com dados válidos deve retornar token com sucesso")
    void autenticarFuncionarioValidoDeveRetornarTokenTeste() {
        Funcionario funcionario = new Funcionario(42, "Carlos", "00000000000", "11900000000", "carlos@email.com", "senha");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(funcionario.getEmail(), funcionario.getSenha());

        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(token)).thenReturn(authenticationMock);
        when(funcionarioGateway.buscarPorEmail(funcionario.getEmail())).thenReturn(Optional.of(funcionario));
        when(gerenciadorTokenJwt.generateToken(authenticationMock)).thenReturn("token.jwt.exemplo");

        FuncionarioTokenDto resultado = autenticarFuncionarioUseCase.autenticar(funcionario.getEmail(), funcionario.getSenha());

        assertNotNull(resultado);
        assertEquals("token.jwt.exemplo", resultado.getToken());
        assertEquals("Carlos", resultado.getNome());
    }

}