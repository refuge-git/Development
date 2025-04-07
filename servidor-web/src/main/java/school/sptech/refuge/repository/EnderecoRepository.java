package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
