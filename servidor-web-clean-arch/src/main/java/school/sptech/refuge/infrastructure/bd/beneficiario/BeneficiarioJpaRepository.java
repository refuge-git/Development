package school.sptech.refuge.infrastructure.bd.beneficiario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;

import java.util.List;


public interface BeneficiarioJpaRepository extends JpaRepository<BeneficiarioEntity, Integer> {


    List<BeneficiarioEntity> findByNomeRegistroContainingIgnoreCase(String nomeRegistro);

    @Query("SELECT b FROM BeneficiarioEntity b WHERE b.raca = :raca")
    List<BeneficiarioEntity> findByRaca(@Param("raca") String raca);

    @Query("SELECT b FROM BeneficiarioEntity b WHERE b.status = :status")
    List<BeneficiarioEntity> findByStatus(@Param("status") String status);


}
