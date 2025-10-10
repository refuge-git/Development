package school.sptech.refuge.core.application.exception;

public class StatusInvalidoExeption extends RuntimeException {

    public StatusInvalidoExeption() {
    }
    public StatusInvalidoExeption(String message) {
        super(message);
    }
}
