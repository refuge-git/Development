package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.TipoGenero;
import school.sptech.refuge.entity.TipoSexualidade;

import java.util.List;

public interface TipoSexualidadeRepository extends JpaRepository<TipoSexualidade, Integer> {
    List<TipoSexualidade> findByDescricaoContainingIgnoreCase(String descricao);
}
