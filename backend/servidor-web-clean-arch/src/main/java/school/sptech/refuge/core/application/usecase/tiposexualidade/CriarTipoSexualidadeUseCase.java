package school.sptech.refuge.core.application.usecase.tiposexualidade;

import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeRequestDto;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

public class CriarTipoSexualidadeUseCase {

    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public CriarTipoSexualidadeUseCase(TipoSexualidadeGateway tipoSexualidadeGateway) {
        this.tipoSexualidadeGateway = tipoSexualidadeGateway;
    }

    public TipoSexualidadeListDto execute(TipoSexualidadeRequestDto tipoSexualidadeRequestDto) {
        TipoSexualidade tipoSexualidade = new TipoSexualidade();
        tipoSexualidade.setNome(tipoSexualidadeRequestDto.getNome());
        tipoSexualidade.setDescricao(tipoSexualidadeRequestDto.getDescricao());

        TipoSexualidade tipoSexualidadeCriado = tipoSexualidadeGateway.salvar(tipoSexualidade);
        return new TipoSexualidadeListDto(
                tipoSexualidadeCriado.getId(),
                tipoSexualidadeCriado.getNome(),
                tipoSexualidadeCriado.getDescricao()
        );
    }
}
