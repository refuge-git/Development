package school.sptech.refuge.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.core.domain.beneficiario.valueobject.Beneficiario;
import school.sptech.refuge.core.domain.beneficiario.valueobject.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.valueobject.SexoEnum;

import java.util.List;


public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {


    List<Beneficiario> findByNomeSocialContainingIgnoreCase(String nome);

    List<Beneficiario> findByNomeRegistroContainingIgnoreCase(String nome);

    @Query("SELECT b FROM Beneficiario b WHERE b.raca = :raca")
    List<Beneficiario> findByRaca(@Param("raca") RacaEnum raca);

    @Query("SELECT b FROM Beneficiario b WHERE b.sexo = :sexo")
    List<Beneficiario> findBySexo(@Param("sexo") SexoEnum sexo);

    @Query("SELECT b FROM Beneficiario b WHERE b.tipoGenero.nome = :nomeGenero")
    List<Beneficiario> findByNomeTipoGenero(@Param("nomeGenero") String nomeGenero);

    @Query("SELECT b FROM Beneficiario b WHERE b.tipoSexualidade.nome = :nomeSexualidade")
    List<Beneficiario> findByNomeTipoSexualidade(@Param("nomeSexualidade") String nomeSexualidade);

}
