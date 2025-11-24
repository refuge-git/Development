package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.time.LocalDate;
import java.util.List;

public interface TipoAtendimentoJpaRepository extends JpaRepository<TipoAtendimentoEntity, Integer> {


    @Query("""
        SELECT DISTINCT ta.id
        FROM RegistroAtendimentoEntity r
        JOIN r.tipoAtendimento ta
        WHERE r.beneficiario.id = :beneficiarioId
          AND DATE(r.dataHora) = :data
    """)
    List<Integer> findIdsRealizadosNaData(
            @Param("beneficiarioId") Integer beneficiarioId,
            @Param("data") LocalDate data
    );

}
