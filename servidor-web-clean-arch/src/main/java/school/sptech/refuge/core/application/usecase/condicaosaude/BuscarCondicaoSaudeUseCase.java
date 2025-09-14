package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarCondicaoSaudeUseCase {
    private final CondicaoSaudeGateway condicaoSaudeGateway;

    public BuscarCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
    }

    public CondicaoSaudeListDto execute(Integer id){
        CondicaoSaude condicaoSaude = condicaoSaudeGateway.buscarPorId(id)
                .orElseThrow(() -> new CondicaoSaudeNaoEncontradaException("Tipo de Condição de Saúde não encontrada para o id: " + id));

        return new CondicaoSaudeListDto(
                condicaoSaude.getId(),
                condicaoSaude.getDiagnostico(),
                condicaoSaude.getDescricao(),
                condicaoSaude.getDataRegistro(),
                condicaoSaude.getDataAtualizacao(),
                condicaoSaude.getTratamento(),
                condicaoSaude.getObservacoes(),
                condicaoSaude.getBeneficiario().getId(),
                condicaoSaude.getCategoria().getId()
        );
    }

}
