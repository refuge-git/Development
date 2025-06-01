package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.refuge.entity.Beneficiario;

import java.util.List;
import java.util.Optional;


public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {


    List<Beneficiario> findByNomeSocialContainingIgnoreCase(String nome);

    List<Beneficiario> findByNomeRegistroContainingIgnoreCase(String nome);

    @Query("SELECT b FROM Beneficiario b " +
            "LEFT JOIN FETCH b.funcionario " +
            "LEFT JOIN FETCH b.endereco " +
            "LEFT JOIN FETCH b.tipoGenero " +
            "LEFT JOIN FETCH b.tipoSexualidade " +
            "WHERE b.id = :id")
    Optional<Beneficiario> buscarComRelacionamentos(@Param("id") Integer id);

/*
    List<Beneficiario> findByRacaContainingIgnoreCase(String raca);
*/
}
