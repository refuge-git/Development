package school.sptech.refuge.core.application.usecase.funcionario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ListagemFuncionarioUseCase {
        private final FuncionarioGateway funcionarioGateway;

    public ListagemFuncionarioUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public Page<FuncionarioListDto> execute(int page, int size) {
        Page<Funcionario> funcionarios = funcionarioGateway.listarPaginado(page, size);
        List<FuncionarioListDto> dtos = funcionarios.getItems().stream()
                .map(FuncionarioListDto::new)
                .collect(Collectors.toList());
        return new Page<>(dtos, funcionarios.getTotal(), funcionarios.getPage(), funcionarios.getSize());
        }
}
