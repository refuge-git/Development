package school.sptech.refuge.core.application.usecase.tipogenero;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

public class BuscarTipoGeneroPorIdUseCase {

    private final TipoGeneroGateway tipoGeneroGateway;

    public BuscarTipoGeneroPorIdUseCase(TipoGeneroGateway tipoGeneroGateway) {
        this.tipoGeneroGateway = tipoGeneroGateway;
    }

    public TipoGeneroListDto execute(Integer id) {
        TipoGenero tipoGenero = tipoGeneroGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado para o id: " + id));

        return new TipoGeneroListDto(
                tipoGenero.getId(),
                tipoGenero.getNome(),
                tipoGenero.getDescricao()
        );
    }
}
