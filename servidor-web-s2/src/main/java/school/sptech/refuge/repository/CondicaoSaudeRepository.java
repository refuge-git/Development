package school.sptech.refuge.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.refuge.entity.CondicaoSaude;

import java.time.LocalDate;
import java.util.List;

public interface CondicaoSaudeRepository extends JpaRepository<CondicaoSaude, Integer> {
    List<CondicaoSaude> findByDescricaoContainingIgnoreCase(String descricao);

    List<CondicaoSaude> findAllByDataRegistro(LocalDate dataRegistro);

    @Modifying
    @Transactional
    @Query("DELETE FROM CondicaoSaude c WHERE c.beneficiario.id = :id")
    void deleteAllByBeneficiarioId(Integer id);


    List<CondicaoSaude> findByBeneficiarioId(Integer idBeneficiario);
}
