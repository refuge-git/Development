package school.sptech.refuge.core.application.usecase.funcionario;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioMapper;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.List;

public class ListarTodosFuncionariosUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public ListarTodosFuncionariosUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public List<FuncionarioListDto> execute() {
        List<Funcionario> lista = funcionarioGateway.listarTodos();
        return lista
                .stream()
                .map(FuncionarioMapper::of)
                .toList();
    }
}
