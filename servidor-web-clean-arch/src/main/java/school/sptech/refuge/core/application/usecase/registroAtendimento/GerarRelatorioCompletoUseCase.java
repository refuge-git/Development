package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.PublicarRelatorioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorFaixaEtaria;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDiaResponse;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.RelatorioCompleto;

import java.util.List;

public class GerarRelatorioCompletoUseCase {

    private final RegistroAtendimentoGateway gateway;
    private final PublicarRelatorioGateway publicarRelatorioGateway;

    public GerarRelatorioCompletoUseCase(RegistroAtendimentoGateway gateway, PublicarRelatorioGateway publicarRelatorioGateway) {
        this.gateway = gateway;
        this.publicarRelatorioGateway = publicarRelatorioGateway;
    }

    public RelatorioCompleto execute(String email) {
        List<PresencaDia> resultadoPresenca = gateway.contarPresencasPorDiaNoMes();
        PresencaDiaResponse responsePresenca = new PresencaDiaResponse(email, resultadoPresenca);
        List<AtendimentosPorFaixaEtaria> resultadoIdades = gateway.contarAtendimentosPorFaixaEtaria();
        RelatorioCompleto relatorioCompleto = new RelatorioCompleto(responsePresenca, resultadoIdades);
        publicarRelatorioGateway.publicarRelatorioCompleto(relatorioCompleto);
        return relatorioCompleto;
    }
}
