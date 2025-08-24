package school.sptech.refuge.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.funcionario.valueobject.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String email);
}
