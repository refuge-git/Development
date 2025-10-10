package school.sptech.refuge.core.application.usecase.tiposexualidade;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeRequestDto;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

public class AtualizarTipoSexualidadeUseCase {

    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public AtualizarTipoSexualidadeUseCase(TipoSexualidadeGateway tipoSexualidadeGateway) {
        this.tipoSexualidadeGateway = tipoSexualidadeGateway;
    }

    public TipoSexualidadeListDto execute(Integer id, TipoSexualidadeRequestDto request) {
        TipoSexualidade existente = tipoSexualidadeGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrada para o id: " + id));

        existente.setNome(request.getNome());
        existente.setDescricao(request.getDescricao());

        TipoSexualidade atualizado = tipoSexualidadeGateway.atualizar(id,existente);

        return new TipoSexualidadeListDto(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getDescricao()
        );
    }
}
