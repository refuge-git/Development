package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeEntity;

import java.util.List;

public interface TipoSexualidadeRepository extends JpaRepository<TipoSexualidadeEntity, Integer> {
    List<TipoSexualidadeEntity> findByDescricaoContainingIgnoreCase(String descricao);
}
