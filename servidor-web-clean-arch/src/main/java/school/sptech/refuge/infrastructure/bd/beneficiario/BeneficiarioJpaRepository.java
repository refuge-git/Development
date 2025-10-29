package school.sptech.refuge.infrastructure.bd.beneficiario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioFrequenciaProjection;
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
    @Query(value = """
  SELECT 
      b.id_beneficiario AS id,
      b.nome_registro AS nomeRegistro,
      b.nome_social AS nomeSocial,
      COUNT(DISTINCT DATE(ra.data_hora)) AS quantidadePresencas
  FROM beneficiario b
  LEFT JOIN registro_atendimento ra 
      ON b.id_beneficiario = ra.fk_beneficiario 
      AND DAYOFWEEK(ra.data_hora) = :diaSemana
  WHERE b.status = 'ATIVO'
    AND (:search IS NULL 
         OR LOWER(b.nome_registro) LIKE LOWER(CONCAT('%', :search, '%'))
         OR LOWER(b.nome_social) LIKE LOWER(CONCAT('%', :search, '%')))
  GROUP BY b.id_beneficiario
  ORDER BY quantidadePresencas DESC
""",
            countQuery = """
  SELECT COUNT(DISTINCT b.id_beneficiario)
  FROM beneficiario b
  LEFT JOIN registro_atendimento ra 
      ON b.id_beneficiario = ra.fk_beneficiario 
      AND DAYOFWEEK(ra.data_hora) = :diaSemana
  WHERE b.status = 'ATIVO'
    AND (:search IS NULL 
         OR LOWER(b.nome_registro) LIKE LOWER(CONCAT('%', :search, '%'))
         OR LOWER(b.nome_social) LIKE LOWER(CONCAT('%', :search, '%')))
""",
            nativeQuery = true)
    Page<BeneficiarioFrequenciaProjection> findBeneficiariosComMaisPresencasPorDiaSemanaPaginado(
            @Param("diaSemana") int diaSemana,
            @Param("search") String search,
            Pageable pageable);


    @Query(value = """
SELECT b.*
FROM beneficiario b
WHERE 
  (
    (:status = 'ALL' AND b.status <> 'ATIVO')
    OR
    (:status <> 'ALL' AND b.status = :status)
  )
  AND (
    LOWER(b.nome_registro) LIKE LOWER(CONCAT('%', :search, '%'))
    OR LOWER(b.nome_social) LIKE LOWER(CONCAT('%', :search, '%'))
  )
ORDER BY b.nome_registro
""",
            countQuery = """
SELECT COUNT(*)
FROM beneficiario b
WHERE 
  (
    (:status = 'ALL' AND b.status <> 'ATIVO')
    OR
    (:status <> 'ALL' AND b.status = :status)
  )
  AND (
    LOWER(b.nome_registro) LIKE LOWER(CONCAT('%', :search, '%'))
    OR LOWER(b.nome_social) LIKE LOWER(CONCAT('%', :search, '%'))
  )
""",
            nativeQuery = true)
    Page<BeneficiarioEntity> findByStatusByNome(
            @Param("status") String status,
            @Param("search") String search,
            Pageable pageable
    );
    }


