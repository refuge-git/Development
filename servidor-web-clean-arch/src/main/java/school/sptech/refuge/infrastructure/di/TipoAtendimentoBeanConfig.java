package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.usecase.tipoAtendimento.*;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoJpaAdapter;

@Configuration
public class TipoAtendimentoBeanConfig {
    @Bean
    public CriarTipoAtendimentoUseCase criarTipoAtendimentoUseCase(TipoAtendimentoJpaAdapter adapter, FuncionarioJpaAdapter funcionario) {
        return new CriarTipoAtendimentoUseCase(adapter, funcionario);
    }

    @Bean
    public ListarTodosTipoAtendimentoUseCase listarTodosTipoAtendimentoUseCase(TipoAtendimentoJpaAdapter adapter) {
        return new ListarTodosTipoAtendimentoUseCase(adapter);
    }

    @Bean
    public DeletarTipoAtendimentoUseCase deletarTipoAtendimentoUseCase(TipoAtendimentoJpaAdapter adapter) {
        return new DeletarTipoAtendimentoUseCase(adapter);
    }

    @Bean
    public AtualizarTipoAtendimentoUseCase atualizarTipoAtendimentoUseCase(TipoAtendimentoJpaAdapter adapter, FuncionarioJpaAdapter funcionario) {
        return new AtualizarTipoAtendimentoUseCase(adapter, funcionario);
    }


    @Bean
    public BuscarAtividadesRealizadasHojeUseCase buscarAtividadesRealizadasHojeUseCase(TipoAtendimentoGateway gateway) {
        return new BuscarAtividadesRealizadasHojeUseCase(gateway);
    }
}



