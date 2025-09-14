package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.application.usecase.endereco.*;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoJpaAdapter;

@Configuration
public class EnderecoBeanConfig {

    @Bean
    public CriarEnderecoUseCase criarEnderecoUseCase(EnderecoJpaAdapter adapter) {
        return new CriarEnderecoUseCase(adapter);
    }

    @Bean
    public ListarTodosEnderecosUseCase listarTodosEnderecosUseCase(EnderecoJpaAdapter adapter) {
        return new ListarTodosEnderecosUseCase(adapter);
    }

    @Bean
    public BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase(EnderecoJpaAdapter adapter) {
        return new BuscarEnderecoPorIdUseCase(adapter);
    }

    @Bean
    public AtualizarEnderecoUseCase atualizarEnderecoUseCase(EnderecoJpaAdapter adapter) {
        return new AtualizarEnderecoUseCase(adapter);
    }

    @Bean
    public DeletarEnderecoUseCase deletarEnderecoUseCase(EnderecoJpaAdapter adapter) {
        return new DeletarEnderecoUseCase(adapter);
    }
}
