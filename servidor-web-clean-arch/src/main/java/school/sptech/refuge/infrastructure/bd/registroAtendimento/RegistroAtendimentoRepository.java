package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.util.Optional;

public interface RegistroAtendimentoRepository extends JpaRepository<RegistroAtendimentoEntity, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM RegistroAtendimentoEntity r WHERE r.beneficiario.id = :id")
    void deleteAllByBeneficiarioId(Integer id);
    void deleteById(Integer id);
    Optional<RegistroAtendimentoEntity> findTopByBeneficiarioIdAndTipoAtendimentoIdOrderByDataHoraDesc(
            Integer idBeneficiario,
            Integer idTipoAtendimento
    );



}
