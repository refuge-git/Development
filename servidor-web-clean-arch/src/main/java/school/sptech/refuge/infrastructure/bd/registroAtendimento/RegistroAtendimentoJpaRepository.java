package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

public interface RegistroAtendimentoJpaRepository extends JpaRepository<RegistroAtendimentoEntity, Integer> {

}
