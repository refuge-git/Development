package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.beneficiario.StatusEnum;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;

public class AtualizarStatusBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public AtualizarStatusBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public BeneficarioListDto execute(Integer id, String novoStatus) {
        Beneficiario beneficiario = beneficiarioGateway.buscarPorId(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário não encontrado"));

        beneficiario.setStatus(StatusEnum.valueOf(novoStatus.toUpperCase()));

        return BeneficiarioMapper.fromDomain(beneficiarioGateway.atualizar(id, beneficiario));
    }
}
