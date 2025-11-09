package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorFaixaEtaria;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDiaResponse;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.RelatorioCompleto;

import java.util.List;

public interface PublicarRelatorioGateway {
    void publicarRelatorioCompleto(RelatorioCompleto relatorioCompleto);
}
