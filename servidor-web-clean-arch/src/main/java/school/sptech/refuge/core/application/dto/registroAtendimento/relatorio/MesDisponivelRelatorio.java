package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class MesDisponivelRelatorio {
    private String mesReferencia;
    private String mesExibicao;

    public MesDisponivelRelatorio(String mesReferencia, String mesExibicao) {
        this.mesReferencia = mesReferencia;
        this.mesExibicao = mesExibicao;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getMesExibicao() {
        return mesExibicao;
    }

    public void setMesExibicao(String mesExibicao) {
        this.mesExibicao = mesExibicao;
    }
}

