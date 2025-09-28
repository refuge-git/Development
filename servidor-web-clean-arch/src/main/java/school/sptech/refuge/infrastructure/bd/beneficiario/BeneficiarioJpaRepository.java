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

    @Query(value = """
    SELECT b.*,
           COUNT(DISTINCT DATE(ra.data_hora)) AS total_presencas
    FROM beneficiario b
    LEFT JOIN registro_atendimento ra 
           ON b.id_beneficiario = ra.fk_beneficiario
          AND DAYOFWEEK(ra.data_hora) = :diaSemana
    WHERE b.status = 'ATIVO'
    GROUP BY b.id_beneficiario
    ORDER BY total_presencas DESC
""", nativeQuery = true)
    List<BeneficiarioEntity> findBeneficiariosComMaisPresencasPorDiaSemana(@Param("diaSemana") int diaSemana);



}
