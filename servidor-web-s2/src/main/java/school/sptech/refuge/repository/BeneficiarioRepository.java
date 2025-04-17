package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Beneficiario;

import java.util.List;


public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {
   List<Beneficiario> findByGeneroEnumLike(String genero);

    List<Beneficiario> findByRacaLike(String raca);

    List<Beneficiario> findByNomeContaining(String nome);
}
