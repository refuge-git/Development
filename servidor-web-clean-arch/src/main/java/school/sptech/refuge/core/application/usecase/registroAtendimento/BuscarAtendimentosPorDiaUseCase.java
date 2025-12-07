package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosDiaDto;
import school.sptech.refuge.infrastructure.bd.registroAtendimento.RegistroAtendimentoRepository;

import java.time.LocalDate;
import java.util.List;

public class BuscarAtendimentosPorDiaUseCase {

    private final RegistroAtendimentoGateway gateway;

    public BuscarAtendimentosPorDiaUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<AtendimentosDiaDto> execute(LocalDate data) {
        return gateway.buscarAtendimentosPorDia(data);
    }
}

