package school.sptech.refuge.core.application.usecase.funcionario;


import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioAtualizacaoDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

public class AtualizarFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public AtualizarFuncionarioUseCase(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public FuncionarioListDto execute(Integer id, FuncionarioAtualizacaoDto funcionarioAtualizacaoDto) {
        Funcionario funcionarioExistente = funcionarioGateway.buscarPorId(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado de ID: " + id));

        funcionarioExistente
                .setNome(funcionarioAtualizacaoDto.getNome());

        funcionarioExistente
                .setCpf(funcionarioAtualizacaoDto.getCpf());

        funcionarioExistente
                .setTelefone(funcionarioAtualizacaoDto.getTelefone());

        funcionarioExistente
                .setEmail(funcionarioAtualizacaoDto.getEmail());

       // funcionarioExistente
         //       .setSenha(funcionarioAtualizacaoDto.getSenha());

        if (funcionarioAtualizacaoDto.getSenha() != null &&
                !funcionarioAtualizacaoDto.getSenha().isBlank()) {

            funcionarioExistente.setSenha(funcionarioAtualizacaoDto.getSenha());
        }

        Funcionario funcionarioAtualizado = funcionarioGateway.salvar(funcionarioExistente);

        return new FuncionarioListDto(
                funcionarioAtualizado.getId(),
                funcionarioAtualizado.getNome(),
                funcionarioAtualizado.getCpf(),
                funcionarioAtualizado.getTelefone(),
                funcionarioAtualizado.getEmail()
        );

    }
}
