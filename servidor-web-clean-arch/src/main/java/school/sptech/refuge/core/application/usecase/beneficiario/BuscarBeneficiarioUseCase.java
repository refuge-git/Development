package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

public class BuscarBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public BuscarBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public BeneficarioListDto execute(Integer id) {
        Beneficiario beneficiario = beneficiarioGateway.buscarPorId(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário de id " + id + " não encontrado"));

        return BeneficiarioMapper.fromDomain(beneficiario);
    }
}
