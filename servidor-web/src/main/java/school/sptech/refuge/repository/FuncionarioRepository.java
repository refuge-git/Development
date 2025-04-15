package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
