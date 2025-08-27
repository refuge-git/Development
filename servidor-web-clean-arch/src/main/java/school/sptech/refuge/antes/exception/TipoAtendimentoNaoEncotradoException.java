package school.sptech.refuge.antes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TipoAtendimentoNaoEncotradoException extends RuntimeException {
    public TipoAtendimentoNaoEncotradoException(String message) {
        super(message);
    }
}
