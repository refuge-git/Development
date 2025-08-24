package school.sptech.refuge.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.tipogenero.valueobject.TipoGenero;

import java.util.List;

public interface TipoGeneroRepository extends JpaRepository<TipoGenero, Integer> {
    List<TipoGenero> findByDescricaoContainingIgnoreCase(String descricao);
}
