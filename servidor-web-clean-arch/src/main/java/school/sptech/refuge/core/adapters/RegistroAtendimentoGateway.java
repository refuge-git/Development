package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosDiaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosSemanaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.ServicosPorSemanaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosMesDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorFaixaEtaria;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorRacaSexo;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.MesDisponivelRelatorio;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.util.List;
import java.util.Optional;

public interface RegistroAtendimentoGateway {
    RegistroAtendimento salvar(RegistroAtendimento registroAtendimento);
    List<RegistroAtendimento> listarTodos();
    Optional<RegistroAtendimento> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Long contarBeneficiariosAtendidosNoMes();
    List<PresencaDia> contarPresencasPorDiaNoMes(String mesReferencia);
    List<AtendimentosPorFaixaEtaria> contarAtendimentosPorFaixaEtaria(String mesReferencia);
    List<AtendimentosPorRacaSexo> contarAtendimentosRacaSexoNoMes(String mesReferencia);
    List<AtendimentosMesDto> buscarAtendimentosMes();
    List<ServicosPorSemanaDto> buscarServicosPorSemana();
    List<AtendimentosDiaDto> buscarAtendimentosDia();
    List<AtendimentosSemanaDto> buscarAtendimentosSemana();
    long getAtendimentosMesAtual();
    long getAtendimentosDiaAtual();
    Long getAtendimentosMesAnterior();
    long getNovosCadastrosMes();
    long getNovosCadastrosMesAnterior();
    long getMediaAtendimentoMesAtual();
    Double getMediaAtividadeMaisRequisitada();
    Double getMediaSegundaAtividadeMaisRequisitada();
    String getAtividadeMaisRequisitadaMes();
    String getAtividadeMaisRequisitadaDia();
    String getSegundaAtividadeMaisRequisitadaMes();
    List<MesDisponivelRelatorio> getMesesDisponiveisRelatorio();
}
