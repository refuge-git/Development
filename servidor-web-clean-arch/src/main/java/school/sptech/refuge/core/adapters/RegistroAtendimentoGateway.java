package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.util.List;
import java.util.Optional;

public interface RegistroAtendimentoGateway {
    RegistroAtendimento salvar(RegistroAtendimento registroAtendimento);
    List<RegistroAtendimento> listarTodos();
    Optional<RegistroAtendimento> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
}
