package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

import java.util.List;

public class RelatorioCompleto {

    private PresencaDiaResponse presencaDiaResponses;
    private List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias;

    public RelatorioCompleto(PresencaDiaResponse presencaDiaResponses, List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias) {
        this.presencaDiaResponses = presencaDiaResponses;
        this.atendimentosPorFaixaEtarias = atendimentosPorFaixaEtarias;
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
}
