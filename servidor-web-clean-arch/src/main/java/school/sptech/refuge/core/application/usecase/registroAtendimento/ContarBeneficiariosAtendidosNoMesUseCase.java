package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;

public class ContarBeneficiariosAtendidosNoMesUseCase {

    private final RegistroAtendimentoGateway registroAtendimentoGateway;

    public ContarBeneficiariosAtendidosNoMesUseCase(RegistroAtendimentoGateway registroAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;
    }

    public Long execute() {
        return registroAtendimentoGateway.contarBeneficiariosAtendidosNoMes();
    }
}
