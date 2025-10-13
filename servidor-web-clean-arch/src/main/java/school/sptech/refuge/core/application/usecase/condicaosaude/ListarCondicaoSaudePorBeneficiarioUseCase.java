package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

import java.util.List;
import java.util.stream.Collectors;

public class ListarCondicaoSaudePorBeneficiarioUseCase {

    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public ListarCondicaoSaudePorBeneficiarioUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public List<CondicaoSaudeListDto> execute(Integer idBeneficiario) {
        List<CondicaoSaude> condicoes = condicaoSaudeGateway.buscarPorBeneficiarioId(idBeneficiario);
        return condicoes.stream()
                .map(CondicaoSaudeListDto::new)
                .collect(Collectors.toList());
    }
}
