package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.PublicarRelatorioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;

import java.util.List;

public class ContarPresencasPorDiaNoMesUseCase {

    private final RegistroAtendimentoGateway gateway;
    private final PublicarRelatorioGateway publicarRelatorioGateway;

    public ContarPresencasPorDiaNoMesUseCase(RegistroAtendimentoGateway gateway, PublicarRelatorioGateway publicarRelatorioGateway) {
        this.gateway = gateway;
        this.publicarRelatorioGateway = publicarRelatorioGateway;
    }

    public List<PresencaDia> execute() {
        List<PresencaDia> resultado = gateway.contarPresencasPorDiaNoMes();
        publicarRelatorioGateway.publicarPresencasPorDia(resultado);

        return resultado;
    }
}
