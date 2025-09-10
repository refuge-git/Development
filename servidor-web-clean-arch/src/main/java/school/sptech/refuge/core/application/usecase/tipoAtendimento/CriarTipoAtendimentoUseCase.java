package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.application.usecase.funcionario.BuscarFuncionarioUseCase;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class CriarTipoAtendimentoUseCase {
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public CriarTipoAtendimentoUseCase(TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public TipoAtendimentoResponseDto execute(TipoAtendimento tipoAtendimento) {
        Funcionario funcionario = (tipoAtendimento.getFuncionario().getId());

    }
}
