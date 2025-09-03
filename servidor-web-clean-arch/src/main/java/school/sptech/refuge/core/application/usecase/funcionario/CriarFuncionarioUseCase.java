package school.sptech.refuge.core.application.usecase.funcionario;

import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioRequestDto;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

public class CriarFuncionarioUseCase {
    private final FuncionarioGateway funcionarioGateway;

    public CriarFuncionarioUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public FuncionarioListDto execute(FuncionarioRequestDto funcionarioRequestDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioRequestDto.getNome());
        funcionario.setCpf(funcionarioRequestDto.getCpf());
        funcionario.setTelefone(funcionarioRequestDto.getTelefone());
        funcionario.setEmail(funcionarioRequestDto.getEmail());
        funcionario.setSenha(funcionarioRequestDto.getSenha());
        Funcionario funcionarioCriado = funcionarioGateway.salvar(funcionario);
        return new FuncionarioListDto(
                funcionarioCriado.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getEmail()
        );
    }

}
