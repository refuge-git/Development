package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.antes.entity.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String email);
}
