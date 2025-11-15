package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

import java.util.List;

public class RelatorioCompleto {

    private PresencaDiaResponse presencaDiaResponses;
    private List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias;
    private List<AtendimentosPorRacaSexo> atendimentosPorRacaSexos;

    public RelatorioCompleto(PresencaDiaResponse presencaDiaResponses, List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias, List<AtendimentosPorRacaSexo> atendimentosPorRacaSexos) {
        this.presencaDiaResponses = presencaDiaResponses;
        this.atendimentosPorFaixaEtarias = atendimentosPorFaixaEtarias;
        this.atendimentosPorRacaSexos = atendimentosPorRacaSexos;
    }

    public PresencaDiaResponse getPresencaDiaResponses() {
        return presencaDiaResponses;
    }

    public void setPresencaDiaResponses(PresencaDiaResponse presencaDiaResponses) {
        this.presencaDiaResponses = presencaDiaResponses;
    }

    public List<AtendimentosPorFaixaEtaria> getAtendimentosPorFaixaEtarias() {
        return atendimentosPorFaixaEtarias;
    }

    public void setAtendimentosPorFaixaEtarias(List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias) {
        this.atendimentosPorFaixaEtarias = atendimentosPorFaixaEtarias;
    }

    public List<AtendimentosPorRacaSexo> getAtendimentosPorRacaSexos() {
        return atendimentosPorRacaSexos;
    }

    public void setAtendimentosPorRacaSexos(List<AtendimentosPorRacaSexo> atendimentosPorRacaSexos) {
        this.atendimentosPorRacaSexos = atendimentosPorRacaSexos;
    }
}
