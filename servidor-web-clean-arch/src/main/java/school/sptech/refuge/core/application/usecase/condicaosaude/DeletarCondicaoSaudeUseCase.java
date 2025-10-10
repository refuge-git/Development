package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;

public class DeletarCondicaoSaudeUseCase {

    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public DeletarCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public void execute(Integer id) {
        if (!condicaoSaudeGateway.existePorId(id)) {
            throw new CondicaoSaudeNaoEncontradaException(
                    "Condição de saúde com id " + id + " não encontrada");
        }
        condicaoSaudeGateway.deletar(id);
    }
}

