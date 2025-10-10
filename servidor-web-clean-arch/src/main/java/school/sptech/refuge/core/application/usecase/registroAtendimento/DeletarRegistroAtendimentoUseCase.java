package school.sptech.refuge.core.application.usecase.registroAtendimento;

import org.springframework.dao.DataIntegrityViolationException;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.exception.RegistroAtendimentoNaoEncontradoException;
import school.sptech.refuge.core.application.exception.ViolacaoDeDadosException;

public class DeletarRegistroAtendimentoUseCase {

    private final RegistroAtendimentoGateway registroAtendimentoGateway;

    public DeletarRegistroAtendimentoUseCase(RegistroAtendimentoGateway registroAtendimentoGateway) {
        this.registroAtendimentoGateway = registroAtendimentoGateway;
    }

    public void execute(Integer id) {
        if (!registroAtendimentoGateway.existePorId(id)) {
            throw new RegistroAtendimentoNaoEncontradoException("Registro de atendimento de id %d não encontrado".formatted(id));
        }

        registroAtendimentoGateway.deletar(id);
    }
}
