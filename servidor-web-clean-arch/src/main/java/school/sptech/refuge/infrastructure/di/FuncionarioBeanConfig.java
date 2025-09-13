package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.refuge.core.application.usecase.funcionario.*;
import school.sptech.refuge.infrastructure.bd.funcionario.AutenticacaoGatewayImpl;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaAdapter;

@Configuration
public class FuncionarioBeanConfig {

    @Bean
    public CriarFuncionarioUseCase criarFuncionarioUseCase(FuncionarioJpaAdapter adapter, PasswordEncoder passwordEncoder) {
        return new CriarFuncionarioUseCase(adapter, passwordEncoder);
    }

    @Bean
    public ListarTodosFuncionariosUseCase listarTodosFuncionariosUseCase(FuncionarioJpaAdapter adapter) {
        return new ListarTodosFuncionariosUseCase(adapter);
    }

    @Bean
    public DeletarFuncionarioUseCase deletarFuncionariioUseCase(FuncionarioJpaAdapter adapter) {
        return new DeletarFuncionarioUseCase(adapter);
    }

    @Bean
    public BuscarFuncionarioUseCase buscarFuncionarioUseCase(FuncionarioJpaAdapter adapter) {
        return new BuscarFuncionarioUseCase(adapter);
    }

    @Bean
    public AtualizarFuncionarioUseCase atualizarFuncionarioUseCase(FuncionarioJpaAdapter adapter) {
        return new AtualizarFuncionarioUseCase(adapter);
    }

    @Bean
    public AutenticarFuncionarioUseCase autenticarFuncionarioUseCase(FuncionarioJpaAdapter adapter, AutenticacaoGatewayImpl autenticacaoGateway) {
        return new AutenticarFuncionarioUseCase(adapter, autenticacaoGateway);
    }
}
