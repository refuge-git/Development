package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

public class AtualizarCondicaoSaudeUseCase {

    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public AtualizarCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public CondicaoSaudeListDto execute(Integer id, CondicaoSaudeRequestDto requestDto) {
        CondicaoSaude existente = condicaoSaudeGateway.buscarPorId(id)
                .orElseThrow(() -> new CondicaoSaudeNaoEncontradaException(
                        "Condição de saúde com id " + id + " não encontrada"));

        existente.setDescricao(requestDto.getDescricao());

        CondicaoSaude atualizada = condicaoSaudeGateway.salvar(existente);

        return new CondicaoSaudeListDto(
                atualizada.getId(),
                atualizada.getDescricao(),
                atualizada.getCategoria().getNome(),
                atualizada.getBeneficiario().getNome()
        );
    }
}

