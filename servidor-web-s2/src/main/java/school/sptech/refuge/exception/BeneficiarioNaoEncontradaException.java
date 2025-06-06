package school.sptech.refuge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeneficiarioNaoEncontradaException extends RuntimeException {
    public BeneficiarioNaoEncontradaException(String message) {
        super(message);
    }
}
