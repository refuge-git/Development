package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class DeletarTipoAtendimentoUseCase {
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public DeletarTipoAtendimentoUseCase(TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public void execute(Integer id) {
        if (!tipoAtendimentoGateway.existePorId(id)) {
            throw new TipoAtendimentoNaoEncotradoException("Tipo Atendimento não encontrado de ID: " + id);
        }
        tipoAtendimentoGateway.deletar(id);
    }

}
