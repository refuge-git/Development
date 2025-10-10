package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;

public class DeletarBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public DeletarBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public void execute(Integer id) {
        if (!beneficiarioGateway.existePorId(id)) {
            throw new BeneficiarioNaoEncontradaException("Beneficiário não encontrado para exclusão");
        }
        beneficiarioGateway.deletar(id);
    }
}
