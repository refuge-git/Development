package school.sptech.refuge.core.application.usecase.funcionario;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioMapper;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.List;

public class ListarTodosFuncionariosUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public ListarTodosFuncionariosUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public List<FuncionarioListDto> listarTodos() {
        return funcionarioGateway.listarTodos()
                .stream()
                .map(FuncionarioMapper::of)
                .toList();
    }
}
