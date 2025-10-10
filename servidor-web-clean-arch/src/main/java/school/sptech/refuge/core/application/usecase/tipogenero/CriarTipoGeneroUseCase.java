package school.sptech.refuge.core.application.usecase.tipogenero;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

public class CriarTipoGeneroUseCase {

    private final TipoGeneroGateway tipoGeneroGateway;

    public CriarTipoGeneroUseCase(TipoGeneroGateway tipoGeneroGateway) {
        this.tipoGeneroGateway = tipoGeneroGateway;
    }

    public TipoGeneroListDto execute(TipoGeneroRequestDto tipoGeneroRequestDto) {
        TipoGenero tipoGenero = new TipoGenero();
        tipoGenero.setNome(tipoGeneroRequestDto.getNome());
        tipoGenero.setDescricao(tipoGeneroRequestDto.getDescricao());

        TipoGenero tipoGeneroCriado = tipoGeneroGateway.salvar(tipoGenero);
        return new TipoGeneroListDto(
                tipoGeneroCriado.getId(),
                tipoGeneroCriado.getNome(),
                tipoGeneroCriado.getDescricao()
        );
    }
}

