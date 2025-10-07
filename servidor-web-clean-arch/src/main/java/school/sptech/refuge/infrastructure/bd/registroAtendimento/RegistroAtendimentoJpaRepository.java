package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;
import java.util.List;

public interface RegistroAtendimentoJpaRepository extends JpaRepository<RegistroAtendimentoEntity, Integer> {


    @Query(value = """
        SELECT COUNT(DISTINCT fk_beneficiario)
        FROM registro_atendimento
        WHERE MONTH(data_hora) = MONTH(CURRENT_DATE())
          AND YEAR(data_hora) = YEAR(CURRENT_DATE())
        """, nativeQuery = true)
    Long countBeneficiariosAtendidosNoMes();


    @Query(value = """
        SELECT DAY(data_hora) AS dia,
               COUNT(DISTINCT fk_beneficiario) AS qtd_pessoas
        FROM registro_atendimento
        WHERE MONTH(data_hora) = MONTH(CURRENT_DATE())
          AND YEAR(data_hora) = YEAR(CURRENT_DATE())
        GROUP BY DAY(data_hora)
        ORDER BY dia
        """, nativeQuery = true)
    List<PresencaDia> countPresencasPorDiaNoMes();


    @Query(value = """
        SELECT b.nome_registro,
               COUNT(DISTINCT DATE(ra.data_hora)) AS total_presencas
        FROM beneficiario b
        LEFT JOIN registro_atendimento ra
               ON b.id_beneficiario = ra.fk_beneficiario
               AND MONTH(ra.data_hora) = MONTH(CURRENT_DATE())
               AND YEAR(ra.data_hora) = YEAR(CURRENT_DATE())
        WHERE b.status = 'ATIVO'
        GROUP BY b.id_beneficiario, b.nome_registro
        ORDER BY total_presencas DESC
        """, nativeQuery = true)
    List<Object[]> listarBeneficiariosMaisPresentes();


    @Query(value = """
        SELECT
            CASE
                WHEN TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 0 AND 5 THEN '0-5'
                WHEN TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 6 AND 11 THEN '6-11'
                WHEN TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 12 AND 14 THEN '12-14'
                WHEN TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 15 AND 17 THEN '15-17'
                WHEN TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 18 AND 29 THEN '18-29'
                WHEN TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 30 AND 59 THEN '30-59'
                ELSE '60+'
            END AS faixa_etaria,
            COUNT(DISTINCT b.id_beneficiario) AS total
        FROM beneficiario b
        JOIN registro_atendimento ra
          ON b.id_beneficiario = ra.fk_beneficiario
         AND MONTH(ra.data_hora) = MONTH(CURRENT_DATE())
         AND YEAR(ra.data_hora) = YEAR(CURRENT_DATE())
        GROUP BY faixa_etaria
        """, nativeQuery = true)
    List<Object[]> contarBeneficiariosPorFaixaEtaria();


    @Query(value = """
        SELECT b.sexo, COUNT(DISTINCT b.id_beneficiario)
        FROM beneficiario b
        JOIN registro_atendimento ra
          ON b.id_beneficiario = ra.fk_beneficiario
         AND MONTH(ra.data_hora) = MONTH(CURRENT_DATE())
         AND YEAR(ra.data_hora) = YEAR(CURRENT_DATE())
        GROUP BY b.sexo
        """, nativeQuery = true)
    List<Object[]> contarBeneficiariosPorSexo();


    @Query(value = """
        SELECT b.raca, COUNT(DISTINCT b.id_beneficiario)
        FROM beneficiario b
        JOIN registro_atendimento ra
          ON b.id_beneficiario = ra.fk_beneficiario
         AND MONTH(ra.data_hora) = MONTH(CURRENT_DATE())
         AND YEAR(ra.data_hora) = YEAR(CURRENT_DATE())
        GROUP BY b.raca
        """, nativeQuery = true)
    List<Object[]> contarBeneficiariosPorRaca();


    @Query(value = """
        SELECT g.nome, COUNT(DISTINCT b.id_beneficiario)
        FROM beneficiario b
        JOIN tipo_genero g ON b.fk_genero = g.id_genero
        JOIN registro_atendimento ra
          ON b.id_beneficiario = ra.fk_beneficiario
         AND MONTH(ra.data_hora) = MONTH(CURRENT_DATE())
         AND YEAR(ra.data_hora) = YEAR(CURRENT_DATE())
        GROUP BY g.nome
        """, nativeQuery = true)
    List<Object[]> contarBeneficiariosPorGenero();


    @Query(value = """
        SELECT COUNT(DISTINCT b.id_beneficiario)
        FROM beneficiario b
        JOIN registro_atendimento ra
          ON b.id_beneficiario = ra.fk_beneficiario
         AND MONTH(ra.data_hora) = MONTH(CURRENT_DATE())
         AND YEAR(ra.data_hora) = YEAR(CURRENT_DATE())
        WHERE b.estrangeiro = true
        """, nativeQuery = true)
    Long contarBeneficiariosEstrangeiros();
}
