package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.application.usecase.tipogenero.*;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroJpaAdapter;

@Configuration
public class TipoGeneroBeanConfig {

    @Bean
    public CriarTipoGeneroUseCase criarTipoGeneroUseCase(TipoGeneroJpaAdapter adapter){
        return new CriarTipoGeneroUseCase(adapter);
    }

    @Bean
    public ListarTodosTipoGeneroUseCase listarTodosTipoGeneroUseCase(TipoGeneroJpaAdapter adapter) {
        return new ListarTodosTipoGeneroUseCase(adapter);
    }

    @Bean
    public BuscarTipoGeneroPorIdUseCase buscarTipoGeneroPorIdUseCase(TipoGeneroJpaAdapter adapter) {
        return new BuscarTipoGeneroPorIdUseCase(adapter);
    }

    @Bean
    public AtualizarTipoGeneroUseCase atualizarTipoGeneroUseCase(TipoGeneroJpaAdapter adapter) {
        return new AtualizarTipoGeneroUseCase(adapter);
    }

    @Bean
    public DeletarTipoGeneroUseCase deletarTipoGeneroUseCase(TipoGeneroJpaAdapter adapter) {
        return new DeletarTipoGeneroUseCase(adapter);
    }

}
