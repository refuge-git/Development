package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public ListarTodosBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public List<BeneficarioListDto> execute() {
        return beneficiarioGateway.listarTodos()
                .stream()
                .map(BeneficiarioMapper::fromDomain)
                .collect(Collectors.toList());
    }
}
