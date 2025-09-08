package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import school.sptech.refuge.core.application.usecase.categoria.CriarCategoriaUseCase;

@Configuration
public class CategoriaBeanConfig {

    @Bean
    public CriarCategoriaUseCase criarCategoriaUseCase(){
        return new CriarCategoriaUseCase(adapter);
    }

}
