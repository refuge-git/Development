package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class PresencaDia {
    private final Long dia;
    private final Long quantidadePessoas;

    public PresencaDia(Long dia, Long quantidadePessoas) {
        this.dia = dia;
        this.quantidadePessoas = quantidadePessoas;
    }

    public Long getDia() {
        return dia;
    }

    public Long getQuantidadePessoas() {
        return quantidadePessoas;
    }
}
