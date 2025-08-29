package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;

import java.util.List;

public interface TipoGeneroRepository extends JpaRepository<TipoGeneroEntity, Integer> {
    List<TipoGeneroEntity> findByDescricaoContainingIgnoreCase(String descricao);
}
