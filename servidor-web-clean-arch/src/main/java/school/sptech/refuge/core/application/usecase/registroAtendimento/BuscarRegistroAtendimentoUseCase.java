package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.RegistroAtendimentoNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class BuscarRegistroAtendimentoUseCase {
    private final RegistroAtendimentoGateway registroAtendimentoGateway;
    private final BeneficiarioGateway beneficiarioGateway;
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public BuscarRegistroAtendimentoUseCase(RegistroAtendimentoGateway registroAtendimentoGateway, BeneficiarioGateway beneficiarioGateway, TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;
        this.beneficiarioGateway = beneficiarioGateway;
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public RegistroAtendimentoResponseDto execute(Integer id) {
        RegistroAtendimento registroAtendimentoExistente = registroAtendimentoGateway.buscarPorId(id)
                .orElseThrow(() -> new RegistroAtendimentoNaoEncontradoException("Registro de atendimento de id %d não encontrado".formatted(id)));

        Beneficiario beneficiarioExistente = beneficiarioGateway.buscarPorId(registroAtendimentoExistente.getBeneficiario().getId())
                        .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiario não encontrado de ID " + registroAtendimentoExistente.getBeneficiario().getId()));

        TipoAtendimento tipoAtendimentoExistente = tipoAtendimentoGateway.buscarPorId(registroAtendimentoExistente.getTipoAtendimento().getId())
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento não encontrado de ID " + registroAtendimentoExistente.getTipoAtendimento().getId()));

        return new RegistroAtendimentoResponseDto(
                registroAtendimentoExistente.getId(),
                registroAtendimentoExistente.getDataHora(),
                tipoAtendimentoExistente.getId(),
                beneficiarioExistente.getId()
        );
    }
}
