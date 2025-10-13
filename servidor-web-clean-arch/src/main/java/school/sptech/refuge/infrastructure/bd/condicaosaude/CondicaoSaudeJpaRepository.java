package school.sptech.refuge.infrastructure.bd.condicaosaude;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CondicaoSaudeJpaRepository extends JpaRepository<CondicaoSaudeEntity, Integer> {


    List<CondicaoSaudeEntity> findByBeneficiarioEntityId(Integer idBeneficiario);
}
