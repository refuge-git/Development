package school.sptech.refuge.core.application.usecase.tipogenero;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

public class AtualizarTipoGeneroUseCase {

    private final TipoGeneroGateway tipoGeneroGateway;

    public AtualizarTipoGeneroUseCase(TipoGeneroGateway tipoGeneroGateway) {
        this.tipoGeneroGateway = tipoGeneroGateway;
    }

    public TipoGeneroListDto execute(Integer id, TipoGeneroRequestDto request) {
        TipoGenero existente = tipoGeneroGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado para o id: " + id));

        existente.setNome(request.getNome());
        existente.setDescricao(request.getDescricao());

        TipoGenero atualizado = tipoGeneroGateway.atualizar(id, existente);

        return new TipoGeneroListDto(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getDescricao()
        );
    }
}
