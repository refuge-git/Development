package school.sptech.refuge.core.application.usecase.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.refuge.core.adapters.AutenticacaoGateway;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioTokenDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.funcionario.Token;
import school.sptech.refuge.infrastructure.config.SecurityLogger;

public class AutenticarFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;
    private final AutenticacaoGateway autenticacaoGateway;
    @Autowired
    SecurityLogger securityLogger;

    public AutenticarFuncionarioUseCase(FuncionarioGateway funcionarioGateway,
                                        AutenticacaoGateway autenticacaoGateway) {
        this.funcionarioGateway = funcionarioGateway;
        this.autenticacaoGateway = autenticacaoGateway;
    }

    public FuncionarioTokenDto autenticar(String email, String senha) {
        try {
            UserDetails userDetails = autenticacaoGateway.autenticar(email, senha);

            Funcionario funcionario = funcionarioGateway.buscarPorEmail(email)
                    .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));

            String token = autenticacaoGateway.gerarToken(userDetails);

            FuncionarioTokenDto dto = new FuncionarioTokenDto();
            dto.setUserId(funcionario.getId());
            dto.setNome(funcionario.getNome());
            dto.setEmail(funcionario.getEmail());
            dto.setToken(token);

            return dto;

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            securityLogger.logSecurityLogin(
                    "Tentativa de login falha",
                    "Método autenticar (email/senha), funcionário service",
                    email
            );
            throw new ResponseStatusException(401, "Credenciais inválidas", e);

        } catch (FuncionarioNaoEncontradaException e) {
            securityLogger.logSecurityLogin(
                    "Tentativa de login com usuário não encontrado",
                    "Método autenticar (email/senha), funcionário service",
                    email
            );
            throw new ResponseStatusException(404, "Funcionário não encontrado", e);

        } catch (Exception e) {
            throw new ResponseStatusException(500, "Erro durante autenticação", e);
        }
    }

}
