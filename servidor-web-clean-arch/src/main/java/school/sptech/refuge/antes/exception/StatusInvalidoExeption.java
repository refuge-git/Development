package school.sptech.refuge.antes.exception;

public class StatusInvalidoExeption extends RuntimeException {

    public StatusInvalidoExeption() {
    }
    public StatusInvalidoExeption(String message) {
        super(message);
    }
}
