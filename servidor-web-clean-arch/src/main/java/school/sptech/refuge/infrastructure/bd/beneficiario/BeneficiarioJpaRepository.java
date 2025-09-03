package school.sptech.refuge.infrastructure.bd.beneficiario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;

import java.util.List;


public interface BeneficiarioJpaRepository extends JpaRepository<BeneficiarioEntity, Integer> {


    List<BeneficiarioEntity> findByNomeSocialContainingIgnoreCase(String nome);

    List<BeneficiarioEntity> findByNomeRegistroContainingIgnoreCase(String nome);

    @Query("SELECT b FROM Beneficiario b WHERE b.raca = :raca")
    List<BeneficiarioEntity> findByRaca(@Param("raca") RacaEnum raca);

    @Query("SELECT b FROM Beneficiario b WHERE b.sexo = :sexo")
    List<BeneficiarioEntity> findBySexo(@Param("sexo") SexoEnum sexo);

    @Query("SELECT b FROM Beneficiario b WHERE b.tipoGenero.nome = :nomeGenero")
    List<BeneficiarioEntity> findByNomeTipoGenero(@Param("nomeGenero") String nomeGenero);

    @Query("SELECT b FROM Beneficiario b WHERE b.tipoSexualidade.nome = :nomeSexualidade")
    List<BeneficiarioEntity> findByNomeTipoSexualidade(@Param("nomeSexualidade") String nomeSexualidade);

}
