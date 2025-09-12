package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class AtualizarTipoAtendimentoUseCase {
    private final TipoAtendimentoGateway tipoAtendimentoGateway;
    private final FuncionarioGateway funcionarioGateway;

    public AtualizarTipoAtendimentoUseCase(TipoAtendimentoGateway tipoAtendimentoGateway, FuncionarioGateway funcionarioGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
        this.funcionarioGateway = funcionarioGateway;
    }

    public TipoAtendimentoResponseDto execute(Integer id, TipoAtendimentoRequestDto tipoAtendimento) {
        TipoAtendimento atendimentoExistente = tipoAtendimentoGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo Atendimento não encontrado de ID: " + id));

        Funcionario funcionarioExistente = funcionarioGateway.buscarPorId(tipoAtendimento.getIdFuncionario())
                        .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado de ID " + tipoAtendimento.getFuncionario().getId()));


        atendimentoExistente.setNome(tipoAtendimento.getNome());
        atendimentoExistente.setDescricao(tipoAtendimento.getDescricao());
        atendimentoExistente.setDataCriacao(tipoAtendimento.getDtCriacao());

        TipoAtendimento tipoAtendimentoAtualizado = tipoAtendimentoGateway.salvar(atendimentoExistente);

        return new TipoAtendimentoResponseDto(
                tipoAtendimentoAtualizado.getId(),
                tipoAtendimentoAtualizado.getNome(),
                tipoAtendimentoAtualizado.getDescricao(),
                tipoAtendimentoAtualizado.getDataCriacao(),
                funcionarioExistente
        );
    }
}
