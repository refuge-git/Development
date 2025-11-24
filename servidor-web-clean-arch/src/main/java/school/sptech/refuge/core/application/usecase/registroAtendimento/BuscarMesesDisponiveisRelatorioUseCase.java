package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.MesDisponivelRelatorio;

import java.util.List;

public class BuscarMesesDisponiveisRelatorioUseCase {

    private final RegistroAtendimentoGateway registroAtendimentoGateway;

    public BuscarMesesDisponiveisRelatorioUseCase(RegistroAtendimentoGateway registroAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;
    }

    public List<MesDisponivelRelatorio> execute() {
        return registroAtendimentoGateway.getMesesDisponiveisRelatorio();
    }
}
