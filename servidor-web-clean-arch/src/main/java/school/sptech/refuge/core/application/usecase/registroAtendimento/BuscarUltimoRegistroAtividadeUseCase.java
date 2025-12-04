package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;
import school.sptech.refuge.infrastructure.bd.registroAtendimento.RegistroAtendimentoEntity;
import school.sptech.refuge.infrastructure.bd.registroAtendimento.RegistroAtendimentoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class BuscarUltimoRegistroAtividadeUseCase {
    private final RegistroAtendimentoRepository repository;

    public BuscarUltimoRegistroAtividadeUseCase(RegistroAtendimentoRepository repository) {
        this.repository = repository;
    }

    public Optional<LocalDateTime> buscar(Integer idBeneficiario, Integer idTipoAtendimento) {
        return repository
                .findTopByBeneficiarioIdAndTipoAtendimentoIdOrderByDataHoraDesc(idBeneficiario, idTipoAtendimento)
                .map(RegistroAtendimentoEntity::getDataHora); // ✔ Agora funciona
    }
}
