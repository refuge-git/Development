package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosDiaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosMesDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosSemanaDto;

import java.util.List;

public class BuscarAtendimentosPorMesUseCase {

    private final RegistroAtendimentoGateway gateway;

    public BuscarAtendimentosPorMesUseCase(RegistroAtendimentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<AtendimentosDiaDto> obterAtendimentosDia() {
        return gateway.buscarAtendimentosDia();
    }

    public List<AtendimentosSemanaDto> obterAtendimentosSemana() {
        return gateway.buscarAtendimentosSemana();
    }

    public List<AtendimentosMesDto> obterAtendimentosMes() {
        return gateway.buscarAtendimentosMes();
    }
}
