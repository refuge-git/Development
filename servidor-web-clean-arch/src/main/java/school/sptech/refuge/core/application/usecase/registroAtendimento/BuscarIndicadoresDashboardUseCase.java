package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.IndicadoresDashboardDto;

import java.util.List;

public class BuscarIndicadoresDashboardUseCase {

    private final RegistroAtendimentoGateway gateway;

    public BuscarIndicadoresDashboardUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public IndicadoresDashboardDto executar() {
        long atendimentosMesAtual = gateway.getAtendimentosMesAtual();
        long atendimentosDiaAtual = gateway.getAtendimentosDiaAtual();
        Long atendimentosMesAnterior = gateway.getAtendimentosMesAnterior();
        long novosCadastrosMes = gateway.getNovosCadastrosMes();
        long novosCadastrosMesAnterior = gateway.getNovosCadastrosMesAnterior();
        long mediaAtendimentosMesAtual = gateway.getMediaAtendimentoMesAtual();
        Double mediaAtividadeMaisRequisitada = gateway.getMediaAtividadeMaisRequisitada();
        Double mediaSegundaAtividadeMaisRequisitada = gateway.getMediaSegundaAtividadeMaisRequisitada();
        String atividadeMaisRequisitada = gateway.getAtividadeMaisRequisitadaMes();
        String atividadeMaisRequisitadaDia = gateway.getAtividadeMaisRequisitadaDia();
        String SegundaAtividadeMaisRequisitada = gateway.getSegundaAtividadeMaisRequisitadaMes();

        double percentualAtendimentos = calcularPercentual(atendimentosMesAtual, atendimentosMesAnterior);
        double percentualCadastros = calcularPercentual(novosCadastrosMes, novosCadastrosMesAnterior);

        return new IndicadoresDashboardDto(
                atendimentosMesAtual,
                atendimentosDiaAtual,
                novosCadastrosMes,
                mediaAtendimentosMesAtual,
                mediaAtividadeMaisRequisitada,
                mediaSegundaAtividadeMaisRequisitada,
                atividadeMaisRequisitada,
                atividadeMaisRequisitadaDia,
                SegundaAtividadeMaisRequisitada,
                percentualAtendimentos,
                percentualCadastros

        );
    }

    private double calcularPercentual(long atual, long anterior) {
        if (anterior == 0) return atual > 0 ? 100.0 : 0.0;
        return ((double) (atual - anterior) / anterior) * 100.0;
    }
}
