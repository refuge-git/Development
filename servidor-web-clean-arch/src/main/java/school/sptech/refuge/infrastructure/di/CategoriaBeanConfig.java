package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import school.sptech.refuge.core.application.usecase.categoria.*;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaAdapter;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaRepository;

@Configuration
public class CategoriaBeanConfig {

    @Bean
    public CriarCategoriaUseCase criarCategoriaUseCase(CategoriaJpaAdapter adapter){
        return new CriarCategoriaUseCase(adapter);
    }

    @Bean
    public ListarTodasCategoriaUseCase listarTodasCategoriaUseCase(CategoriaJpaAdapter adapter){
        return new ListarTodasCategoriaUseCase(adapter);
    }

    @Bean
    public BuscarCategoriaUseCase buscarCategoriaUseCase(CategoriaJpaAdapter adapter){
        return new BuscarCategoriaUseCase(adapter);
    }

    @Bean
    public AtualizarCategoriaUseCase atualizarCategoriaUseCase(CategoriaJpaAdapter adapter){
        return new AtualizarCategoriaUseCase(adapter);
    }

    @Bean
    public DeletarCategoriaUseCase deletarCategoriaUseCase(CategoriaJpaAdapter adapter){
        return new DeletarCategoriaUseCase(adapter);
    }
}
