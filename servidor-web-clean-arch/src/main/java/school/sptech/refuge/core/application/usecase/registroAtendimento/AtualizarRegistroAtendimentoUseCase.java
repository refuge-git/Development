package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.RegistroAtendimentoNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public class AtualizarRegistroAtendimentoUseCase {


    private final RegistroAtendimentoGateway registroAtendimentoGateway;
    private final BeneficiarioGateway beneficiarioGateway;
    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public AtualizarRegistroAtendimentoUseCase(RegistroAtendimentoGateway registroAtendimentoGateway, BeneficiarioGateway beneficiarioGateway, TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;
        this.beneficiarioGateway = beneficiarioGateway;
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public RegistroAtendimentoResponseDto execute(Integer id, RegistroAtendimentoRequestDto registroAtendimentoRequestDto) {
        RegistroAtendimento registroAtendimentoExistente = registroAtendimentoGateway.buscarPorId(id)
                .orElseThrow(() -> new RegistroAtendimentoNaoEncontradoException("Registro de atendimento de id %d não encontrado".formatted(id)));

        Beneficiario beneficiarioExistente = beneficiarioGateway.buscarPorId(registroAtendimentoRequestDto.getIdBeneficiario())
                        .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiario não encontrado de ID " + registroAtendimentoRequestDto.getIdBeneficiario()));

        TipoAtendimento tipoAtendimentoExistente = tipoAtendimentoGateway.buscarPorId(registroAtendimentoRequestDto.getIdTipoAtendimento())
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento não encontrado de ID " + registroAtendimentoRequestDto.getIdTipoAtendimento()));

        registroAtendimentoExistente.setDataHora(registroAtendimentoRequestDto.getDataHora());
        registroAtendimentoExistente.setTipoAtendimento(tipoAtendimentoExistente);
        registroAtendimentoExistente.setBeneficiario(beneficiarioExistente);

        RegistroAtendimento registroAtendimentoAtualizado = registroAtendimentoGateway.salvar(registroAtendimentoExistente);

        return new RegistroAtendimentoResponseDto(
                registroAtendimentoAtualizado.getId(),
                registroAtendimentoAtualizado.getDataHora(),
                tipoAtendimentoExistente.getId(),
                beneficiarioExistente.getId()
        );
        }
    }
