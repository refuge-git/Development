package school.sptech.refuge.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.refuge.entity.RegistroAtendimento;

public interface RegistroAtendimentoRepository extends JpaRepository<RegistroAtendimento, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM RegistroAtendimento r WHERE r.beneficiario.id = :id")
    void deleteAllByBeneficiarioId(Integer id);
}
