package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosDeLavagemRoupa {

    private Long numeroAtendimentosLavagemRoupa;

    public AtendimentosDeLavagemRoupa() {
    }

    public AtendimentosDeLavagemRoupa(Long numeroAtendimentosLavagemRoupa) {
        this.numeroAtendimentosLavagemRoupa = numeroAtendimentosLavagemRoupa;
    }

    public Long getNumeroAtendimentosLavagemRoupa() {
        return numeroAtendimentosLavagemRoupa;
    }

    public void setNumeroAtendimentosLavagemRoupa(Long numeroAtendimentosLavagemRoupa) {
        this.numeroAtendimentosLavagemRoupa = numeroAtendimentosLavagemRoupa;
    }
}
