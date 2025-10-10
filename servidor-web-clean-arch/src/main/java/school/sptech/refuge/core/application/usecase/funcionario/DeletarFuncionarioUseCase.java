package school.sptech.refuge.core.application.usecase.funcionario;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;

public class DeletarFuncionarioUseCase {
    private final FuncionarioGateway funcionarioGateway;

    public DeletarFuncionarioUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public void execute(Integer id) {
        if (!funcionarioGateway.existePorId(id)){
            throw new FuncionarioNaoEncontradaException("Funcionário não encontrado de ID: " + id);
        }
        funcionarioGateway.deletar(id);
    }

}
