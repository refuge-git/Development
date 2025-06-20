package school.sptech.refuge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistroAtendimentoNaoEncontradoException extends RuntimeException {
    public RegistroAtendimentoNaoEncontradoException(String message) {
        super(message);
    }
}
