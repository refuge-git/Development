package school.sptech.refuge.core.application.dto.registroAtendimento;

import java.util.List;

public class IndicadoresDashboardDto {
    private long atendimentosMesAtual;
    private long novosCadastrosMes;
    private long mediaAtendimentosMesAtual;
    private Double mediaAtividadeMaisRequisitada;
    private Double mediaSegundaAtividadeMaisRequisitada;
    private String atividadeMaisRequisitadaMes;
    private String segundaAtividadeMaisRequisitadaMes;

    // Percentuais de variação (mês atual vs mês anterior). Pode ser positivo ou negativo.
    private double crescimentoAtendimentos;     // ex: 51.4
    private double crescimentoNovosCadastros;  // ex: -15.0

    public IndicadoresDashboardDto(long atendimentosMesAtual, long novosCadastrosMes, long mediaAtendimentosMesAtual, List<Object[]> mediaAtividadeMaisRequisitada, List<Object[]> mediaSegundaAtividadeMaisRequisitada, String atividadeMaisRequisitada, String segundaAtividadeMaisRequisitada, double percentualAtendimentos, double percentualCadastros) {
    }

    public IndicadoresDashboardDto(long atendimentosMesAtual, long novosCadastrosMes, long mediaAtendimentosMesAtual, Double mediaAtividadeMaisRequisitada, Double mediaSegundaAtividadeMaisRequisitada, String atividadeMaisRequisitadaMes, String segundaAtividadeMaisRequisitadaMes, double crescimentoAtendimentos, double crescimentoNovosCadastros) {
        this.atendimentosMesAtual = atendimentosMesAtual;
        this.novosCadastrosMes = novosCadastrosMes;
        this.mediaAtendimentosMesAtual = mediaAtendimentosMesAtual;
        this.mediaAtividadeMaisRequisitada = mediaAtividadeMaisRequisitada;
        this.mediaSegundaAtividadeMaisRequisitada = mediaSegundaAtividadeMaisRequisitada;
        this.atividadeMaisRequisitadaMes = atividadeMaisRequisitadaMes;
        this.segundaAtividadeMaisRequisitadaMes = segundaAtividadeMaisRequisitadaMes;
        this.crescimentoAtendimentos = crescimentoAtendimentos;
        this.crescimentoNovosCadastros = crescimentoNovosCadastros;
    }

    public long getAtendimentosMesAtual() {
        return atendimentosMesAtual;
    }

    public void setAtendimentosMesAtual(long atendimentosMesAtual) {
        this.atendimentosMesAtual = atendimentosMesAtual;
    }

    public long getNovosCadastrosMes() {
        return novosCadastrosMes;
    }

    public void setNovosCadastrosMes(long novosCadastrosMes) {
        this.novosCadastrosMes = novosCadastrosMes;
    }

    public String getAtividadeMaisRequisitadaMes() {
        return atividadeMaisRequisitadaMes;
    }

    public void setAtividadeMaisRequisitadaMes(String atividadeMaisRequisitadaMes) {
        this.atividadeMaisRequisitadaMes = atividadeMaisRequisitadaMes;
    }

    public double getCrescimentoAtendimentos() {
        return crescimentoAtendimentos;
    }

    public double getCrescimentoNovosCadastros() {
        return crescimentoNovosCadastros;
    }

    public String getSegundaAtividadeMaisRequisitadaMes() {
        return segundaAtividadeMaisRequisitadaMes;
    }

    public long getMediaAtendimentosMesAtual() {
        return mediaAtendimentosMesAtual;
    }

    public Double getMediaAtividadeMaisRequisitada() {
        return mediaAtividadeMaisRequisitada;
    }

    public Double getMediaSegundaAtividadeMaisRequisitada() {
        return mediaSegundaAtividadeMaisRequisitada;
    }
}
