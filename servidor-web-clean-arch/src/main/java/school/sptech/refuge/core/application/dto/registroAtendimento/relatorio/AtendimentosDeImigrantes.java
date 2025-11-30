package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosDeImigrantes {

    private Long numeroImigrantesAtendidos;

    public AtendimentosDeImigrantes() {
    }

    public AtendimentosDeImigrantes(Long numeroImigrantesAtendidos) {
        this.numeroImigrantesAtendidos = numeroImigrantesAtendidos;
    }

    public Long getNumeroImigrantesAtendidos() {
        return numeroImigrantesAtendidos;
    }

    public void setNumeroImigrantesAtendidos(Long numeroImigrantesAtendidos) {
        this.numeroImigrantesAtendidos = numeroImigrantesAtendidos;
    }
}
