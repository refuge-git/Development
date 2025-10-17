package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.ServicosPorSemanaDto;

import java.util.List;

public class BuscarServicosPorSemanaUseCase {
    private final RegistroAtendimentoGateway gateway;

    public BuscarServicosPorSemanaUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<ServicosPorSemanaDto> executar() {
        return gateway.buscarServicosPorSemana();
    }
}