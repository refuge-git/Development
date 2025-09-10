package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class AtualizarTipoAtendimentoUseCase {
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public AtualizarTipoAtendimentoUseCase(TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public TipoAtendimento execute(Integer id, TipoAtendimento tipoAtendimento) {
        TipoAtendimento atendimentoExistente = tipoAtendimentoGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo Atendimento não encontrado de ID: " + id));

        atendimentoExistente.setNome(tipoAtendimento.getNome());
        atendimentoExistente.setDescricao(tipoAtendimento.getDescricao());
        atendimentoExistente.setDataCriacao(tipoAtendimento.getDataCriacao());

        TipoAtendimento tipoAtendimentoAtualizado = tipoAtendimentoGateway.salvar(atendimentoExistente);

        return new FuncionarioListDto(
                tipoAtendimentoAtualizado.getId(),
                tipoAtendimentoAtualizado.getNome(),
                tipoAtendimentoAtualizado.getDescricao(),
                tipoAtendimentoAtualizado.getDataCriacao()
        );
    }
}
