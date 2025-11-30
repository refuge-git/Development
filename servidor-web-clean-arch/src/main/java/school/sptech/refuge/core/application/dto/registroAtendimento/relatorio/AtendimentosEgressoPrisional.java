package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosEgressoPrisional {

    private Long numeroEgressosAtendidos;

    public AtendimentosEgressoPrisional() {
    }

    public AtendimentosEgressoPrisional(Long numeroEgressosAtendidos) {
        this.numeroEgressosAtendidos = numeroEgressosAtendidos;
    }

    public Long getNumeroEgressosAtendidos() {
        return numeroEgressosAtendidos;
    }

    public void setNumeroEgressosAtendidos(Long numeroEgressosAtendidos) {
        this.numeroEgressosAtendidos = numeroEgressosAtendidos;
    }
}
