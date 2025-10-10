package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ListagemBeneficiarioUseCase {
    private final BeneficiarioGateway beneficiarioGateway;

    public ListagemBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public Page<BeneficarioListDto> execute(int page, int size) {
        Page<Beneficiario> beneficiarios = beneficiarioGateway.listarPaginado(page, size);
        List<BeneficarioListDto> dtos = beneficiarios.getItems().stream()
                .map(BeneficarioListDto::new)
                .collect(Collectors.toList());
        return new Page<>(dtos, beneficiarios.getTotal(), beneficiarios.getPage(), beneficiarios.getSize());
    }

}
