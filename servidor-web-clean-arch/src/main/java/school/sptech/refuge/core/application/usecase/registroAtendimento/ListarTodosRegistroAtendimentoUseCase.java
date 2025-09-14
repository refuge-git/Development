package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoMapper;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.util.List;

public class ListarTodosRegistroAtendimentoUseCase {
    private final RegistroAtendimentoGateway registroAtendimentoGateway;

    public ListarTodosRegistroAtendimentoUseCase(RegistroAtendimentoGateway registroAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;

    }

    public List<RegistroAtendimentoResponseDto> execute() {
        List<RegistroAtendimento> lista = registroAtendimentoGateway.listarTodos();
        return lista
                .stream()
                .map(RegistroAtendimentoMapper::of)
                .toList();
    }
}
