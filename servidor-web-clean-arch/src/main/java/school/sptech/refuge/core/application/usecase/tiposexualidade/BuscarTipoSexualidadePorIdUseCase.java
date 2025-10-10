package school.sptech.refuge.core.application.usecase.tiposexualidade;

import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

public class BuscarTipoSexualidadePorIdUseCase {

    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public BuscarTipoSexualidadePorIdUseCase(TipoSexualidadeGateway tipoSexualidadeGateway) {
        this.tipoSexualidadeGateway = tipoSexualidadeGateway;
    }

    public TipoSexualidadeListDto execute(Integer id) {
        TipoSexualidade tipoSexualidade = tipoSexualidadeGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrada para o id: " + id));

        return new TipoSexualidadeListDto(
                tipoSexualidade.getId(),
                tipoSexualidade.getNome(),
                tipoSexualidade.getDescricao()
        );
    }
}
