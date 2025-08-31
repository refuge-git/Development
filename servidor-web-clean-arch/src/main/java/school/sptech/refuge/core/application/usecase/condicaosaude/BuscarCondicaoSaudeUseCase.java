package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarCondicaoSaudeUseCase {
    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public BuscarCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public List<CondicaoSaudeListDto> execute(String descricao) {
        return condicaoSaudeGateway.buscarPorDescricao(descricao)
                .stream()
                .map(c -> new CondicaoSaudeListDto(
                        c.getId(),
                        c.getDescricao(),
                        c.getCategoria().getNome(),
                        c.getBeneficiario().getNome()
                ))
                .collect(Collectors.toList());
    }
}
