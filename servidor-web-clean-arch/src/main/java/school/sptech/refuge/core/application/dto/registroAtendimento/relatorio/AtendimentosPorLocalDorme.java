package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosPorLocalDorme {

    private String localDorme;
    private Long numeroAtendimentosLocalDorme;

    public AtendimentosPorLocalDorme() {
    }

    public AtendimentosPorLocalDorme(String localDorme, Long numeroAtendimentosLocalDorme) {
        this.localDorme = localDorme;
        this.numeroAtendimentosLocalDorme = numeroAtendimentosLocalDorme;
    }

    public String getLocalDorme() {
        return localDorme;
    }

    public void setLocalDorme(String localDorme) {
        this.localDorme = localDorme;
    }

    public Long getNumeroAtendimentosLocalDorme() {
        return numeroAtendimentosLocalDorme;
    }

    public void setNumeroAtendimentosLocalDorme(Long numeroAtendimentosLocalDorme) {
        this.numeroAtendimentosLocalDorme = numeroAtendimentosLocalDorme;
    }
}
