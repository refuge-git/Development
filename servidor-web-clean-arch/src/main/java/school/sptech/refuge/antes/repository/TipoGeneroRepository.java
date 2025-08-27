package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.antes.entity.TipoGenero;

import java.util.List;

public interface TipoGeneroRepository extends JpaRepository<TipoGenero, Integer> {
    List<TipoGenero> findByDescricaoContainingIgnoreCase(String descricao);
}
