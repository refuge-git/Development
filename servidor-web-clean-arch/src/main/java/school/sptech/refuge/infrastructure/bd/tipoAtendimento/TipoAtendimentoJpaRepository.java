package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

public interface TipoAtendimentoJpaRepository extends JpaRepository<TipoAtendimento, Integer> {
}
