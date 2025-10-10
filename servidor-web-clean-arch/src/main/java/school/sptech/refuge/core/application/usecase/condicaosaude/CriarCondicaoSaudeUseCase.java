package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.categoria.Categoria;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeJpaRepository;

public class CriarCondicaoSaudeUseCase {

    private final CondicaoSaudeGateway condicaoSaudeGateway;
    private final CategoriaGateway categoriaGateway;
    private final BeneficiarioGateway beneficiarioGateway;

    public CriarCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway,
                                     CategoriaGateway categoriaGateway,
                                     BeneficiarioGateway beneficiarioGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
        this.categoriaGateway = categoriaGateway;
        this.beneficiarioGateway = beneficiarioGateway;
    }


    public CondicaoSaudeListDto execute(CondicaoSaudeRequestDto requestDto) {

        Categoria categoria = categoriaGateway.buscarPorId(requestDto.getIdCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada"));

        Beneficiario beneficiario = beneficiarioGateway.buscarPorId(requestDto.getIdBeneficiario())
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário não encontrado"));

        CondicaoSaude condicaoSaude = new CondicaoSaude(
                null,
                requestDto.getDiagnostico(),
                requestDto.getDescricao(),
                null,
                null,
                requestDto.getTratamento(),
                requestDto.getObservacoes(),
                beneficiario,
                categoria
        );

        condicaoSaude.prePersist();

        CondicaoSaude criada = condicaoSaudeGateway.salvar(condicaoSaude);

        return new CondicaoSaudeListDto(
                criada.getId(),
                criada.getDiagnostico(),
                criada.getDescricao(),
                criada.getDataRegistro(),
                criada.getDataAtualizacao(),
                criada.getTratamento(),
                criada.getObservacoes(),
                criada.getBeneficiario().getId(),
                criada.getCategoria().getId()
        );
    }
}