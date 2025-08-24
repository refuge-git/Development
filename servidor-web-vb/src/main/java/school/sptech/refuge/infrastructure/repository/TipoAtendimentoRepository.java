package school.sptech.refuge.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.tipoatendimento.valueobject.TipoAtendimento;

public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Integer> {
}
