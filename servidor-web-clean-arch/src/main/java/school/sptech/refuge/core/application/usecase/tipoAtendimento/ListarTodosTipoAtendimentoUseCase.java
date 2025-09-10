package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoMapper;

import java.util.List;

public class ListarTodosTipoAtendimentoUseCase {
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public ListarTodosTipoAtendimentoUseCase(TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public List<TipoAtendimento> execute() {
        List<TipoAtendimento> lista = tipoAtendimentoGateway.listar();
        return lista
                .stream()
                .map(TipoAtendimentoMapper::of)
                .toList();
    }
}
