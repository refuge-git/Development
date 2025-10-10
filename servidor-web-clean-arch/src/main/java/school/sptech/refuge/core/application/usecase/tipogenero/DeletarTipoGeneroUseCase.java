package school.sptech.refuge.core.application.usecase.tipogenero;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;

public class DeletarTipoGeneroUseCase {

    private final TipoGeneroGateway tipoGeneroGateway;

    public DeletarTipoGeneroUseCase(TipoGeneroGateway tipoGeneroGateway) {
        this.tipoGeneroGateway = tipoGeneroGateway;
    }

    public void execute(Integer id) {
        if (!tipoGeneroGateway.existePorId(id)) {
            throw new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado para o id: " + id);
        }
        tipoGeneroGateway.deletar(id);
    }
}
