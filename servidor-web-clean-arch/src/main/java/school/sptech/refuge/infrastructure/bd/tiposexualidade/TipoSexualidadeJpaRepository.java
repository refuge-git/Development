package school.sptech.refuge.infrastructure.bd.tiposexualidade;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;

public interface TipoSexualidadeJpaRepository extends JpaRepository<TipoSexualidadeEntity, Integer> {
}
