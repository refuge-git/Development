package school.sptech.refuge.core.application.usecase.funcionario;

import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.refuge.core.adapters.AutenticacaoGateway;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioTokenDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.funcionario.Token;

public class AutenticarFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;
    private final AutenticacaoGateway autenticacaoGateway;

    public AutenticarFuncionarioUseCase(FuncionarioGateway funcionarioGateway, AutenticacaoGateway autenticacaoGateway) {
        this.funcionarioGateway = funcionarioGateway;
        this.autenticacaoGateway = autenticacaoGateway;
    }

    public FuncionarioTokenDto autenticar(String email, String senha) {
        UserDetails userDetails = autenticacaoGateway.loadUserByUsername(email);

        if (!autenticacaoGateway.validarSenha(senha, userDetails.getPassword())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        Funcionario funcionario = funcionarioGateway.buscarPorEmail(email)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));

        String token = autenticacaoGateway.gerarToken(userDetails);

        FuncionarioTokenDto dto = new FuncionarioTokenDto();
        dto.setUserId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setEmail(funcionario.getEmail());
        dto.setToken(token);

        return dto;
    }
}
