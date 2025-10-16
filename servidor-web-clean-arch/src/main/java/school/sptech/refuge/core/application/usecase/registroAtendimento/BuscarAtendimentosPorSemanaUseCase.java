package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosPorDiaDto;

import java.util.List;

public class BuscarAtendimentosPorSemanaUseCase {
    private final RegistroAtendimentoGateway gateway;

    public BuscarAtendimentosPorSemanaUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<AtendimentosPorDiaDto> executar() {
        return gateway.buscarAtendimentosPorSemana();
    }
}