package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.CondicaoSaude;

import java.time.LocalDate;
import java.util.List;

public interface CondicaoSaudeRepository extends JpaRepository<CondicaoSaude, Integer> {
    List<CondicaoSaude> findByDescricaoContainingIgnoreCase(String descricao);

    List<CondicaoSaude> findAllByDataRegistro(LocalDate dataRegistro);
}
