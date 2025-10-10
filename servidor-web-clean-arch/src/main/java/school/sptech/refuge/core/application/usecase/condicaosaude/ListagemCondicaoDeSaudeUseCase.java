package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ListagemCondicaoDeSaudeUseCase {
    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public ListagemCondicaoDeSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public Page<CondicaoSaudeListDto> execute(int page, int size) {
        Page<CondicaoSaude> condicaoSaudePage = condicaoSaudeGateway.listarPaginado(page, size);
        List<CondicaoSaudeListDto> dtos = condicaoSaudePage.getItems().stream()
                .map(CondicaoSaudeListDto::new)
                .collect(Collectors.toList());
        return new Page<>(dtos, condicaoSaudePage.getTotal(), condicaoSaudePage.getPage(), condicaoSaudePage.getSize());
    }
}
