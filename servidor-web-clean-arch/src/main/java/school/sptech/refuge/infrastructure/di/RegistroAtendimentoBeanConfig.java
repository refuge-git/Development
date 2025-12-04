package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.application.usecase.registroAtendimento.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.registroAtendimento.RabbitMqRelatorioAdapter;
import school.sptech.refuge.infrastructure.bd.registroAtendimento.RegistroAtendimentoJpaAdapter;
import school.sptech.refuge.infrastructure.bd.registroAtendimento.RegistroAtendimentoRepository;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoJpaAdapter;

@Configuration
public class RegistroAtendimentoBeanConfig {

    @Bean
    public CriarRegistroAtendimentoUseCase criarRegistroAtendimentoUseCase(RegistroAtendimentoJpaAdapter adapter, TipoAtendimentoJpaAdapter tipoAtendimento, BeneficiarioJpaAdapter beneficiario) {
        return new CriarRegistroAtendimentoUseCase(adapter, beneficiario, tipoAtendimento);
    }

    @Bean
    public DeletarRegistroAtendimentoUseCase deletarRegistroAtendimentoUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new DeletarRegistroAtendimentoUseCase(adapter);
    }

    @Bean
    public AtualizarRegistroAtendimentoUseCase atualizarRegistroAtendimentoUseCase(RegistroAtendimentoJpaAdapter adapter, BeneficiarioJpaAdapter beneficiario, TipoAtendimentoJpaAdapter tipoAtendimento) {
        return new AtualizarRegistroAtendimentoUseCase(adapter, beneficiario, tipoAtendimento);
    }

    @Bean
    public ListarTodosRegistroAtendimentoUseCase listarTodosRegistroAtendimentoUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new ListarTodosRegistroAtendimentoUseCase(adapter);
    }

    @Bean
    public BuscarRegistroAtendimentoUseCase buscarRegistroAtendimentoUseCase(RegistroAtendimentoJpaAdapter adapter, BeneficiarioJpaAdapter beneficiario, TipoAtendimentoJpaAdapter tipoAtendimento) {
        return new BuscarRegistroAtendimentoUseCase(adapter, beneficiario, tipoAtendimento);
    }

    @Bean
    public ContarBeneficiariosAtendidosNoMesUseCase contarBeneficiariosAtendidosNoMesUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new ContarBeneficiariosAtendidosNoMesUseCase(adapter);
    }


    @Bean
    public BuscarAtendimentosPorMesUseCase buscarAtendimentosPorMesUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new BuscarAtendimentosPorMesUseCase(adapter);
    }

    @Bean
    public BuscarServicosPorSemanaUseCase buscarAtendimentosPorSemanaUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new BuscarServicosPorSemanaUseCase(adapter);
    }

    @Bean
    public BuscarIndicadoresDashboardUseCase buscarIndicadoresDashboardUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new BuscarIndicadoresDashboardUseCase(adapter);
    }

    @Bean
    public GerarRelatorioCompletoUseCase gerarRelatorioCompletoUseCase(RegistroAtendimentoJpaAdapter adapter, RabbitMqRelatorioAdapter rabbitMqRelatorioAdapter) {
        return new GerarRelatorioCompletoUseCase(adapter, rabbitMqRelatorioAdapter);
    }

    @Bean
    public BuscarMesesDisponiveisRelatorioUseCase buscarMesesDisponiveisRelatorioUseCase(RegistroAtendimentoJpaAdapter adapter) {
        return new BuscarMesesDisponiveisRelatorioUseCase(adapter);
    }

    @Bean
    public BuscarUltimoRegistroAtividadeUseCase buscarUltimoRegistroAtividadeUseCase(
            RegistroAtendimentoRepository repository
    ) {
        return new BuscarUltimoRegistroAtividadeUseCase(repository);
    }

}
