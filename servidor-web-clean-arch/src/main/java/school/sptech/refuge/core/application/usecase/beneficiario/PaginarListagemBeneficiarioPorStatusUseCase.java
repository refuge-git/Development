
package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioFrequenciaProjection;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioStatusDto;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PaginarListagemBeneficiarioPorStatusUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public PaginarListagemBeneficiarioPorStatusUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public Page<BeneficiarioStatusDto> execute(String search, String status, int page, int size) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;

        Page<Beneficiario> pageResult =
                beneficiarioGateway.listarPaginadoPorStatus(page, size, status, search);

        List<BeneficiarioStatusDto> dtos = pageResult.getItems().stream()
                .map(b -> new BeneficiarioStatusDto(
                        b.getNomeRegistro(),
                        b.getStatus().name(),
                        b.getFotoPerfil(),
                        null
                ))
                .collect(Collectors.toList());

        return new Page<>(dtos, pageResult.getTotal(), pageResult.getPage(), pageResult.getSize());
    }
}


