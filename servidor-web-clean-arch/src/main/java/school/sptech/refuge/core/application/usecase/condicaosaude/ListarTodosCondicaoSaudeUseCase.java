package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosCondicaoSaudeUseCase {

    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public ListarTodosCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public List<CondicaoSaudeListDto> execute() {
        return condicaoSaudeGateway.listarTodos()
                .stream()
                .map(condicaoSaude -> new CondicaoSaudeListDto(
                        condicaoSaude.getId(),
                        condicaoSaude.getDiagnostico(),
                        condicaoSaude.getDescricao(),
                        condicaoSaude.getDataRegistro(),
                        condicaoSaude.getDataAtualizacao(),
                        condicaoSaude.getTratamento(),
                        condicaoSaude.getObservacoes(),
                        condicaoSaude.getBeneficiario().getId(),
                        condicaoSaude.getCategoria().getId()
                ))
                .collect(Collectors.toList());
    }

}
