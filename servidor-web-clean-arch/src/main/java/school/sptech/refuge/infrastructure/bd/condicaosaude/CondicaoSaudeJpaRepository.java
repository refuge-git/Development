package school.sptech.refuge.infrastructure.bd.condicaosaude;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CondicaoSaudeJpaRepository extends JpaRepository<CondicaoSaudeEntity, Integer> {
    List<CondicaoSaudeEntity> findByDescricaoContainingIgnoreCase(String descricao);

    List<CondicaoSaudeEntity> findAllByDataRegistro(LocalDate dataRegistro);

    @Modifying
    @Transactional
    @Query("DELETE FROM CondicaoSaude c WHERE c.beneficiario.id = :id")
    void deleteAllByBeneficiarioId(Integer id);


    List<CondicaoSaudeEntity> findByBeneficiarioId(Integer idBeneficiario);
}
