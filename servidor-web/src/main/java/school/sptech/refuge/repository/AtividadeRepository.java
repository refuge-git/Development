package school.sptech.refuge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
}
