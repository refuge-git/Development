package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Beneficiario;



public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {
}
