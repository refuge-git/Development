package school.sptech.refuge.core.application.usecase.condicaosaude;

import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.categoria.Categoria;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

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
        Categoria categoria = categoriaGateway.buscarPorId(requestDto.getCategoriaId())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada"));

        Beneficiario beneficiario = beneficiarioGateway.buscarPorId(requestDto.getBeneficiarioId())
                .orElseThrow(() -> new BeneficiarioNaoEncontradoException("Beneficiário não encontrado"));

        CondicaoSaude condicaoSaude = new CondicaoSaude();
        condicaoSaude.setDescricao(requestDto.getDescricao());
        condicaoSaude.setCategoria(categoria);
        condicaoSaude.setBeneficiario(beneficiario);

        CondicaoSaude criada = condicaoSaudeGateway.salvar(condicaoSaude);

        return new CondicaoSaudeListDto(
                criada.getId(),
                criada.getDescricao(),
                criada.getCategoria().getNome(),
                criada.getBeneficiario().getNome()
        );
    }
}
