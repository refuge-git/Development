package school.sptech.refuge.core.application.usecase.beneficiario;



import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ListarBeneficiarioPorRacaUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public ListarBeneficiarioPorRacaUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public List<BeneficarioListDto> execute(String raca) {
        return beneficiarioGateway.buscarPorRaca(raca)
                .stream()
                .map(BeneficiarioMapper::fromDomain)
                .collect(Collectors.toList());
    }
}
