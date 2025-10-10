package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;

import java.util.List;

public interface PublicarRelatorioGateway {
    void publicarPresencasPorDia(List<PresencaDia> relatorio);
}
