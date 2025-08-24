package school.sptech.refuge.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.tiposexualidade.valueobject.TipoSexualidade;

import java.util.List;

public interface TipoSexualidadeRepository extends JpaRepository<TipoSexualidade, Integer> {
    List<TipoSexualidade> findByDescricaoContainingIgnoreCase(String descricao);
}
