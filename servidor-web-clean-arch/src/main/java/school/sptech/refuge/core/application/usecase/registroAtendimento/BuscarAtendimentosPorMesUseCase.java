package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosPorMesDto;

import java.util.List;

public class BuscarAtendimentosPorMesUseCase {

    private final RegistroAtendimentoGateway gateway;

    public BuscarAtendimentosPorMesUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<AtendimentosPorMesDto> executar() {
        return gateway.buscarAtendimentosPorMes();
    }
}
