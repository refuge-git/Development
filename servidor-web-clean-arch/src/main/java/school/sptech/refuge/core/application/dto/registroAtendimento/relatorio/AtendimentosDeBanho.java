package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosDeBanho {

    private Long numeroAtendimentosDeBanho;

    public AtendimentosDeBanho() {
    }

    public AtendimentosDeBanho(Long numeroAtendimentosDeBanho) {
        this.numeroAtendimentosDeBanho = numeroAtendimentosDeBanho;
    }

    public Long getNumeroAtendimentosDeBanho() {
        return numeroAtendimentosDeBanho;
    }

    public void setNumeroAtendimentosDeBanho(Long numeroAtendimentosDeBanho) {
        this.numeroAtendimentosDeBanho = numeroAtendimentosDeBanho;
    }
}
