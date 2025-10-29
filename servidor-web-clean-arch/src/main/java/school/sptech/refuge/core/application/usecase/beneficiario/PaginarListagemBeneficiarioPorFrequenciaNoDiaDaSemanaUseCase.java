

package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioFrequenciaDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioFrequenciaProjection;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public Page<BeneficiarioFrequenciaDto> execute(int diaSemana, int page, int size, String search) {
        // Chama o gateway passando o search também
        Page<BeneficiarioFrequenciaProjection> pageResult =
                beneficiarioGateway.listarPaginadoPorFrequencia(page, size, diaSemana, search);
        // Converte para DTO
        List<BeneficiarioFrequenciaDto> dtos = pageResult.getItems()
                .stream()
                .map(p -> new BeneficiarioFrequenciaDto(
                        p.getId().intValue(),
                        p.getNomeRegistro(),
                        p.getNomeSocial(),
                        p.getQuantidadePresencas().intValue()
                ))
                .collect(Collectors.toList());

        // Cria a nova Page com os DTOs
        return new Page<>(dtos, pageResult.getTotal(), pageResult.getPage(), pageResult.getSize());
    }
}
