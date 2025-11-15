package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.PublicarRelatorioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.*;

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
        List<AtendimentosPorRacaSexo> resultadoRacaSexo = gateway.contarAtendimentosRacaSexoNoMes();
        RelatorioCompleto relatorioCompleto = new RelatorioCompleto(responsePresenca, resultadoIdades, resultadoRacaSexo);
        publicarRelatorioGateway.publicarRelatorioCompleto(relatorioCompleto);
        return relatorioCompleto;
    }
}
