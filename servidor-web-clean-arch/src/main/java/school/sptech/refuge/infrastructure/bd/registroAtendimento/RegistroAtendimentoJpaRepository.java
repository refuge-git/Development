package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorFaixaEtaria;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorRacaSexo;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.MesDisponivelRelatorio;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;

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
    WHERE DATE_FORMAT(data_hora, '%Y-%m') = :mes
    GROUP BY DAY(data_hora)
    ORDER BY dia
    """, nativeQuery = true)
    List<PresencaDia> countPresencasPorDiaNoMes(@Param("mes") String mes);



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
    WITH faixas AS (
        SELECT '0 a 5 anos' AS faixa_etaria
        UNION ALL SELECT '6 a 11 anos'
        UNION ALL SELECT '12 a 14 anos'
        UNION ALL SELECT '15 a 17 anos'
        UNION ALL SELECT '18 a 29 anos'
        UNION ALL SELECT '30 a 59 anos'
        UNION ALL SELECT '60 anos ou mais'
    ),
    sexos AS (
        SELECT 'FEMININO' AS sexo
        UNION ALL SELECT 'MASCULINO'
    )
    SELECT
        f.faixa_etaria,
        s.sexo,
        COALESCE(COUNT(DISTINCT b.id_beneficiario), 0) AS numero_pessoas_atendidas
    FROM faixas f
    CROSS JOIN sexos s
    LEFT JOIN registro_atendimento ra
        ON DATE_FORMAT(ra.data_hora, '%Y-%m') = :mes
    LEFT JOIN beneficiario b
        ON ra.fk_beneficiario = b.id_beneficiario
       AND (
            (f.faixa_etaria = '0 a 5 anos' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 0 AND 5) OR
            (f.faixa_etaria = '6 a 11 anos' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 6 AND 11) OR
            (f.faixa_etaria = '12 a 14 anos' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 12 AND 14) OR
            (f.faixa_etaria = '15 a 17 anos' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 15 AND 17) OR
            (f.faixa_etaria = '18 a 29 anos' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 18 AND 29) OR
            (f.faixa_etaria = '30 a 59 anos' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) BETWEEN 30 AND 59) OR
            (f.faixa_etaria = '60 anos ou mais' AND TIMESTAMPDIFF(YEAR, b.dt_nasc, CURDATE()) >= 60)
        )
       AND b.sexo = s.sexo
    GROUP BY f.faixa_etaria, s.sexo
    ORDER BY
        FIELD(f.faixa_etaria,
            '0 a 5 anos','6 a 11 anos','12 a 14 anos','15 a 17 anos',
            '18 a 29 anos','30 a 59 anos','60 anos ou mais'),
        FIELD(s.sexo, 'FEMININO','MASCULINO');
""", nativeQuery = true)
    List<AtendimentosPorFaixaEtaria> contarBeneficiariosPorFaixaEtaria(
            @Param("mes") String mes
    );


    @Query(value = """
    WITH sexos AS (
        SELECT 'FEMININO' AS sexo
        UNION ALL SELECT 'MASCULINO'
    ),
    racas AS (
        SELECT 'BRANCO' AS raca
        UNION ALL SELECT 'PRETO'
        UNION ALL SELECT 'PARDO'
        UNION ALL SELECT 'AMARELA'
        UNION ALL SELECT 'INDIGENA'
        UNION ALL SELECT 'NAO_DECLARADO'
    ),
    atendimentos_mes AS (
        SELECT DISTINCT ra.fk_beneficiario
        FROM registro_atendimento ra
        WHERE DATE_FORMAT(ra.data_hora, '%Y-%m') = :mesReferencia
    )
    SELECT
        s.sexo,
        r.raca,
        COALESCE(COUNT(DISTINCT b.id_beneficiario), 0) AS numero_pessoas_atendidas
    FROM sexos s
    CROSS JOIN racas r
    LEFT JOIN atendimentos_mes am
        ON 1 = 1
    LEFT JOIN beneficiario b
        ON b.id_beneficiario = am.fk_beneficiario
       AND b.sexo = s.sexo
       AND b.raca = r.raca
    GROUP BY
        s.sexo,
        r.raca
    ORDER BY
        FIELD(s.sexo, 'FEMININO','MASCULINO'),
        FIELD(r.raca,'BRANCO','PRETO','PARDO','AMARELA','INDIGENA','NAO_DECLARADO');
""", nativeQuery = true)
    List<AtendimentosPorRacaSexo> contarBeneficiariosPorRacaSexo(
            @Param("mesReferencia") String mesReferencia
    );



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



    @Query(value = """
    SELECT DATE_FORMAT(ra.data_hora, '%b') AS mes,
           MONTH(ra.data_hora) AS mes_numero,
           SUM(CASE WHEN ta.nome = 'banho' THEN 1 ELSE 0 END) AS quantidade_banhos,
           SUM(CASE WHEN ta.nome = 'refeicao' THEN 1 ELSE 0 END) AS quantidade_refeicoes,
           SUM(CASE WHEN ta.nome NOT IN ('banho', 'refeicao') THEN 1 ELSE 0 END) AS quantidade_outros
    FROM registro_atendimento ra
    JOIN tipo_atendimento ta ON ra.fk_tipo = ta.id_tipo_atendimento
    WHERE YEAR(ra.data_hora) = YEAR(CURDATE())
    GROUP BY mes, mes_numero
    ORDER BY mes_numero;
""", nativeQuery = true)
    List<Object[]> buscarServicosPorSemana();

    @Query(value = """
      SELECT DATE_FORMAT(data_hora, '%H:00') AS hora,
             COUNT(*) AS quantidade_atendimentos
      FROM registro_atendimento
      WHERE data_hora BETWEEN CONCAT(CURDATE(), ' 07:00:00')
                         AND CONCAT(CURDATE(), ' 20:00:00')
      GROUP BY DATE_FORMAT(data_hora, '%H:00')
      ORDER BY hora;
    """, nativeQuery = true)
    List<Object[]> buscarAtendimentosDia();

    @Query(value = """
      SELECT DATE_FORMAT(data_hora, '%a') AS dia_semana,
             COUNT(*) AS quantidade_atendimentos
      FROM registro_atendimento
      WHERE data_hora >= DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY)
        AND data_hora <  DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 7 DAY)
      GROUP BY DATE_FORMAT(data_hora, '%a'), DAYOFWEEK(data_hora)
      ORDER BY DAYOFWEEK(data_hora);
    """, nativeQuery = true)
    List<Object[]> buscarAtendimentosSemana();

    @Query(value = """
            SELECT\s
                DATE_FORMAT(dia, '%d/%m') AS dia_mes,
                quantidade_atendimentos
            FROM (
                SELECT\s
                    DATE(data_hora) AS dia,
                    COUNT(*) AS quantidade_atendimentos
                FROM registro_atendimento
                WHERE MONTH(data_hora) = MONTH(CURRENT_DATE())
                  AND YEAR(data_hora) = YEAR(CURRENT_DATE())
                GROUP BY DATE(data_hora)
            ) AS sub
            ORDER BY dia;
""", nativeQuery = true)
    List<Object[]> buscarAtendimentosMes();


    //Total de atendimentos no mês atual
    @Query(value = """
        SELECT COUNT(*) 
        FROM registro_atendimento 
        WHERE MONTH(data_hora) = MONTH(CURDATE())
          AND YEAR(data_hora) = YEAR(CURDATE())
        """, nativeQuery = true)
    long countAtendimentosMesAtual();

    @Query(value = """
        SELECT COUNT(*)
        FROM registro_atendimento
        WHERE DATE(data_hora) = CURDATE()
        """, nativeQuery = true)
    long countAtendimentosDiaAtual();

    @Query(value = """
            SELECT
            AVG(total_atendimentos) AS media_diaria_esperada
            FROM (
            SELECT
            DATE(data_hora) AS dia,
            COUNT(*) AS total_atendimentos
            FROM registro_atendimento
            WHERE DAYOFWEEK(data_hora) = DAYOFWEEK(CURDATE())
            GROUP BY dia
            ) AS medias;
        """, nativeQuery = true)
    long countMediaAtendimentosMesAtual();

    //Total de novos cadastros no mês atual
    @Query(value = """
        SELECT COUNT(*) 
        FROM beneficiario 
        WHERE MONTH(data_ativacao) = MONTH(CURDATE())
          AND YEAR(data_ativacao) = YEAR(CURDATE())
        """, nativeQuery = true)
    long countNovosCadastrosMes();

    //Atividade mais requisitada do mês
    @Query(value = """
        SELECT ta.nome AS atividade_mais_requisitada, COUNT(*) AS total
        FROM registro_atendimento ra
        JOIN tipo_atendimento ta ON ra.fk_tipo = ta.id_tipo_atendimento
        WHERE MONTH(ra.data_hora) = MONTH(CURDATE())
          AND YEAR(ra.data_hora) = YEAR(CURDATE())
        GROUP BY ta.nome
        ORDER BY total DESC
        LIMIT 1
        """, nativeQuery = true)
    List<Object[]> findAtividadeMaisRequisitadaMes();

    @Query(value = """
            SELECT ta.nome AS atividade_mais_requisitada, COUNT(*) AS total
                FROM registro_atendimento ra
                JOIN tipo_atendimento ta ON ra.fk_tipo = ta.id_tipo_atendimento
                WHERE DATE(ra.data_hora) = CURDATE()
                GROUP BY ta.nome
                ORDER BY total DESC
                LIMIT 1
        """, nativeQuery = true)
    List<Object[]> findAtividadeMaisRequisitadaDia();

    @Query(value = """
        SELECT ta.nome AS atividade_mais_requisitada, COUNT(*) AS total
        FROM registro_atendimento ra
        JOIN tipo_atendimento ta ON ra.fk_tipo = ta.id_tipo_atendimento
        WHERE MONTH(ra.data_hora) = MONTH(CURDATE())
          AND YEAR(ra.data_hora) = YEAR(CURDATE())
        GROUP BY ta.nome
        ORDER BY total DESC
        LIMIT 1 OFFSET 1
        """, nativeQuery = true)
    List<Object[]> findSegundaAtividadeMaisRequisitadaMes();



    @Query(value = """
    SELECT COUNT(*)
    FROM registro_atendimento
    WHERE MONTH(data_hora) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))
      AND YEAR(data_hora) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))
    """, nativeQuery = true)
    Long countAtendimentosMesAnterior();

    // Total de novos cadastros no mês anterior
    @Query(value = """
    SELECT COUNT(*)
    FROM beneficiario
    WHERE MONTH(data_ativacao) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))
      AND YEAR(data_ativacao) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))
    """, nativeQuery = true)
    long countNovosCadastrosMesAnterior();


    @Query(value = """
            SELECT
            nome AS atividade,
            ROUND(AVG(total_por_dia), 2) AS media_diaria_esperada
            FROM (
            SELECT
            ta.id_tipo_atendimento,
            ta.nome,
            DATE(ra.data_hora) AS dia,
            COUNT(*) AS total_por_dia
            FROM registro_atendimento ra
            JOIN tipo_atendimento ta ON ra.fk_tipo = ta.id_tipo_atendimento
                           WHERE DAYOFWEEK(ra.data_hora) = DAYOFWEEK(CURDATE())  -- Filtra apenas o mesmo dia da semana de hoje
                           GROUP BY ta.id_tipo_atendimento, ta.nome, dia
                       ) AS historico
                       WHERE id_tipo_atendimento = (
                           -- Subquery para descobrir o tipo de atendimento mais requisitado hoje (mesmo dia da semana)
                           SELECT ta2.id_tipo_atendimento
                           FROM registro_atendimento ra2
                           JOIN tipo_atendimento ta2 ON ra2.fk_tipo = ta2.id_tipo_atendimento
                           WHERE DAYOFWEEK(ra2.data_hora) = DAYOFWEEK(CURDATE())
                           GROUP BY ta2.id_tipo_atendimento
                           ORDER BY COUNT(*) DESC
                           LIMIT 1
                       )
                       GROUP BY nome
                       ;
        """, nativeQuery = true)
    List<Object[]> findMediaAtividadeMaisRequisitada();

    @Query(value = """
            SELECT
                                     nome AS atividade,
                                     ROUND(AVG(total_por_dia), 2) AS media_diaria_esperada
                                 FROM (
                                     SELECT
                                         ta.id_tipo_atendimento,
                                         ta.nome,
                                         DATE(ra.data_hora) AS dia,
                                         COUNT(*) AS total_por_dia
                                     FROM registro_atendimento ra
                                     JOIN tipo_atendimento ta ON ra.fk_tipo = ta.id_tipo_atendimento
                                     WHERE DAYOFWEEK(ra.data_hora) = DAYOFWEEK(CURDATE())
                                     GROUP BY ta.id_tipo_atendimento, ta.nome, dia
                                 ) AS historico
                                 WHERE id_tipo_atendimento = (
                                     -- Subquery para descobrir o segundo tipo de atendimento mais requisitado hoje (mesmo dia da semana)
                                     SELECT ta2.id_tipo_atendimento
                                     FROM registro_atendimento ra2
                                     JOIN tipo_atendimento ta2 ON ra2.fk_tipo = ta2.id_tipo_atendimento
                                     WHERE DAYOFWEEK(ra2.data_hora) = DAYOFWEEK(CURDATE())
                                     GROUP BY ta2.id_tipo_atendimento
                                     ORDER BY COUNT(*) DESC
                                     LIMIT 1 OFFSET 1  -- Pega o segundo mais requisitado
                                 )
                                 GROUP BY nome;
            
        """, nativeQuery = true)
    List<Object[]> findMediaSegundaAtividadeMaisRequisitada();

    @Query(value = """
            SELECT DISTINCT\s
                DATE_FORMAT(data_hora, '%Y-%m') AS mesReferencia,
                DATE_FORMAT(data_hora, '%m/%Y') AS mesExibicao
            FROM registro_atendimento
            ORDER BY mesReferencia DESC
            LIMIT 12;
        """, nativeQuery = true)
    List<MesDisponivelRelatorio> getMesesDisponiveisRelatorio();


}
