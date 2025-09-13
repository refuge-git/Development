package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.application.usecase.condicaosaude.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaAdapter;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeJpaAdapter;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeJpaRepository;

@Configuration
public class CondicaoSaudeBeanConfig {

    @Bean
    public CriarCondicaoSaudeUseCase criarCondicaoSaudeUseCase(CondicaoSaudeJpaAdapter adapter,
                                                               BeneficiarioJpaAdapter beneficiarioAdapter,
                                                               CategoriaJpaAdapter categoriaAdapter){
        return new CriarCondicaoSaudeUseCase(adapter, categoriaAdapter, beneficiarioAdapter);
    }

    @Bean
    public ListarTodosCondicaoSaudeUseCase listarTodosCondicaoSaudeUseCase(CondicaoSaudeJpaAdapter adapter){
        return new ListarTodosCondicaoSaudeUseCase(adapter);
    }

    @Bean
    public BuscarCondicaoSaudeUseCase buscarCondicaoSaudeUseCase(CondicaoSaudeJpaAdapter adapter){
        return new BuscarCondicaoSaudeUseCase(adapter);
    }

    @Bean
    public AtualizarCondicaoSaudeUseCase atualizarCondicaoSaudeUseCase(CondicaoSaudeJpaAdapter adapter,
                                                                       BeneficiarioJpaAdapter beneficiarioAdapter,
                                                                       CategoriaJpaAdapter categoriaAdapter){
        return new AtualizarCondicaoSaudeUseCase(adapter, beneficiarioAdapter , categoriaAdapter);
    }

    @Bean
    public DeletarCondicaoSaudeUseCase deletarCondicaoSaudeUseCase(CondicaoSaudeJpaAdapter adapter){
        return new DeletarCondicaoSaudeUseCase(adapter);
    }

}
