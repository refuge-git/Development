package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;

import java.util.List;

public class ContarPresencasPorDiaNoMesUseCase {

    private final RegistroAtendimentoGateway gateway;

    public ContarPresencasPorDiaNoMesUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<Object[]> execute() {
        return gateway.contarPresencasPorDiaNoMes();
    }
}
