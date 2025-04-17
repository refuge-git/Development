package school.sptech.refuge.exeption;

public class StatusInvalidoExeption extends RuntimeException {

    public StatusInvalidoExeption() {
    }
    public StatusInvalidoExeption(String message) {
        super(message);
    }
}
