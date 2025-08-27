package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.antes.entity.TipoSexualidade;

import java.util.List;

public interface TipoSexualidadeRepository extends JpaRepository<TipoSexualidade, Integer> {
    List<TipoSexualidade> findByDescricaoContainingIgnoreCase(String descricao);
}
