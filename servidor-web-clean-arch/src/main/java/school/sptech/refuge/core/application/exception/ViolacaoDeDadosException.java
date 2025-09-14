package school.sptech.refuge.core.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ViolacaoDeDadosException extends RuntimeException {
    public ViolacaoDeDadosException(String message) {
        super(message);
    }
}
