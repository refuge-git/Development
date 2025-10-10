package school.sptech.refuge.infrastructure.bd.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.Optional;

public interface FuncionarioJpaRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<FuncionarioEntity> findByEmail(String email);
}
