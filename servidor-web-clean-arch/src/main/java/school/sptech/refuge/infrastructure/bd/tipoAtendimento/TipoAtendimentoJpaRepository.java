package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.util.List;

public interface TipoAtendimentoJpaRepository extends JpaRepository<TipoAtendimentoEntity, Integer> {
}
