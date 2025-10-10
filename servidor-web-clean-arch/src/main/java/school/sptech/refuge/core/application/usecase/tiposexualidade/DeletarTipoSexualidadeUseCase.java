package school.sptech.refuge.core.application.usecase.tiposexualidade;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;

public class DeletarTipoSexualidadeUseCase {

    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public DeletarTipoSexualidadeUseCase(TipoSexualidadeGateway tipoSexualidadeGateway) {
        this.tipoSexualidadeGateway = tipoSexualidadeGateway;
    }

    public void execute(Integer id) {
        if (!tipoSexualidadeGateway.existePorId(id)) {
            throw new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrada para o id: " + id);
        }
        tipoSexualidadeGateway.deletar(id);
    }
}
