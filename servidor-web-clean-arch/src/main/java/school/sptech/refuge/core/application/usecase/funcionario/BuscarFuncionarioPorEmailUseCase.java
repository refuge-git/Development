package school.sptech.refuge.core.application.usecase.funcionario;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

public class BuscarFuncionarioPorEmailUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public BuscarFuncionarioPorEmailUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public FuncionarioListDto execute(String email) {
        Funcionario funcionario = funcionarioGateway.buscarPorEmail(email)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado de email: " + email));

        return new FuncionarioListDto(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getEmail()
        );
    }
}
