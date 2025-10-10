package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class CriarRegistroAtendimentoUseCase {

    private final RegistroAtendimentoGateway registroAtendimentoGateway;
    private final BeneficiarioGateway beneficiarioGateway;
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public CriarRegistroAtendimentoUseCase(RegistroAtendimentoGateway registroAtendimentoGateway, BeneficiarioGateway beneficiarioGateway, TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;
        this.beneficiarioGateway = beneficiarioGateway;
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public RegistroAtendimentoResponseDto execute(RegistroAtendimentoRequestDto dto){
        Beneficiario beneficiario = beneficiarioGateway.buscarPorId(dto.getIdBeneficiario())
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiario não encontrado de ID " + dto.getIdBeneficiario()));

        TipoAtendimento tipoAtendimento = tipoAtendimentoGateway.buscarPorId(dto.getIdTipoAtendimento())
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento não encontrado de ID " + dto.getIdTipoAtendimento()));

        RegistroAtendimento registroAtendimento = new RegistroAtendimento(
                null,
                dto.getDataHora(),
                tipoAtendimento,
                beneficiario
        );

        RegistroAtendimento registroAtendimentoCriado = registroAtendimentoGateway.salvar(registroAtendimento);

        return new RegistroAtendimentoResponseDto(
                registroAtendimentoCriado.getId(),
                registroAtendimentoCriado.getDataHora(),
                tipoAtendimento.getId(),
                beneficiario.getId()
        );

    }

}
