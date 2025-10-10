package school.sptech.refuge.core.application.usecase.funcionario;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

public class BuscarFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public BuscarFuncionarioUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public FuncionarioListDto execute(Integer id) {
        Funcionario funcionario = funcionarioGateway.buscarPorId(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado de ID: " + id));

        return new FuncionarioListDto(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getEmail()
        );
    }
}
