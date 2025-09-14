package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.usecase.funcionario.BuscarFuncionarioUseCase;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoMapper;

public class CriarTipoAtendimentoUseCase {
    private final TipoAtendimentoGateway tipoAtendimentoGateway;
    private final FuncionarioGateway funcionarioGateway;

    public CriarTipoAtendimentoUseCase(TipoAtendimentoGateway tipoAtendimentoGateway, FuncionarioGateway funcionarioGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
        this.funcionarioGateway = funcionarioGateway;
    }

    public TipoAtendimentoResponseDto execute(TipoAtendimentoRequestDto dto) {
        Funcionario funcionario = funcionarioGateway.buscarPorId(dto.getIdFuncionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado de ID: " + dto.getIdFuncionario()));

        TipoAtendimento tipoAtendimento = new TipoAtendimento(
                null,
                dto.getNome(),
                dto.getDescricao(),
                dto.getDtCriacao(),
                funcionario
        );



        TipoAtendimento tipoAtendimentoCriado = tipoAtendimentoGateway.salvar(tipoAtendimento);

        return new TipoAtendimentoResponseDto(
                tipoAtendimentoCriado.getId(),
                tipoAtendimentoCriado.getNome(),
                tipoAtendimentoCriado.getDescricao(),
                tipoAtendimentoCriado.getDataCriacao(),
                tipoAtendimentoCriado.getFuncionario().getId()
        );
    }
}
