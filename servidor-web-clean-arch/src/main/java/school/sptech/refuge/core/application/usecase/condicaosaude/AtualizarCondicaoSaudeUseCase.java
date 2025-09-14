package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeAtualizacaoDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.categoria.Categoria;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaAdapter;

public class AtualizarCondicaoSaudeUseCase {

    private final CondicaoSaudeGateway condicaoSaudeGateway;
    private final BeneficiarioGateway beneficiarioGateway;
    private final CategoriaGateway categoriaGateway;

    public AtualizarCondicaoSaudeUseCase(CondicaoSaudeGateway condicaoSaudeGateway, BeneficiarioGateway beneficiarioGateway, CategoriaGateway categoriaGateway) {
        this.condicaoSaudeGateway = condicaoSaudeGateway;
        this.beneficiarioGateway = beneficiarioGateway;
        this.categoriaGateway = categoriaGateway;
    }

    public CondicaoSaudeListDto execute(Integer id, CondicaoSaudeAtualizacaoDto dtoAtualizacao) {
        if(!condicaoSaudeGateway.existePorId(id)){
            throw new CondicaoSaudeNaoEncontradaException("Condigão de Saúde não encontrada");
        }
        Categoria categoria = categoriaGateway.buscarPorId(dtoAtualizacao.getIdCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada"));

        Beneficiario beneficiario = beneficiarioGateway.buscarPorId(dtoAtualizacao.getIdBeneficiario())
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário não encontrado"));

        CondicaoSaude condicaoSaude = new CondicaoSaude(
                id,
                dtoAtualizacao.getDiagnostico(),
                dtoAtualizacao.getDescricao(),
                null,
                null,
                dtoAtualizacao.getTratamento(),
                dtoAtualizacao.getObservacoes(),
                beneficiario,
                categoria
        );

        CondicaoSaude atualizada = condicaoSaudeGateway.atualizar(id, condicaoSaude);

        return new CondicaoSaudeListDto(
                atualizada.getId(),
                atualizada.getDiagnostico(),
                atualizada.getDescricao(),
                atualizada.getDataRegistro(),
                atualizada.getDataAtualizacao(),
                atualizada.getTratamento(),
                atualizada.getObservacoes(),
                atualizada.getBeneficiario().getId(),
                atualizada.getCategoria().getId()

        );
    }
}

