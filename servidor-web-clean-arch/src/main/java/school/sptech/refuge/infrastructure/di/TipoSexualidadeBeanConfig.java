package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import school.sptech.refuge.core.application.usecase.tiposexualidade.*;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeJpaAdapter;


public class TipoSexualidadeBeanConfig {
    @Bean
    public CriarTipoSexualidadeUseCase criarTipoSexualidadeUseCase(TipoSexualidadeJpaAdapter adapter){
        return new CriarTipoSexualidadeUseCase(adapter);
    }

    @Bean
    public ListarTodosTipoSexualidadeUseCase listarTodosTipoSexualidadeUseCase(TipoSexualidadeJpaAdapter adapter) {
        return new ListarTodosTipoSexualidadeUseCase(adapter);
    }

    @Bean
    public BuscarTipoSexualidadePorIdUseCase buscarTipoSexualidadePorIdUseCase(TipoSexualidadeJpaAdapter adapter) {
        return new BuscarTipoSexualidadePorIdUseCase(adapter);
    }

    @Bean
    public AtualizarTipoSexualidadeUseCase atualizarTipoSexualidadeUseCase(TipoSexualidadeJpaAdapter adapter) {
        return new AtualizarTipoSexualidadeUseCase(adapter);
    }

    @Bean
    public DeletarTipoSexualidadeUseCase deletarTipoSexualidadeUseCase(TipoSexualidadeJpaAdapter adapter) {
        return new DeletarTipoSexualidadeUseCase(adapter);
    }
}
