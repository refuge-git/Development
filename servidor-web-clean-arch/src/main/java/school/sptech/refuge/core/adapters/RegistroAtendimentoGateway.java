package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosDiaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosSemanaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.ServicosPorSemanaDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosMesDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.*;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.time.LocalDate;
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
    List<AtendimentosPorIdentidadeGenero> contarAtendimentosIdentidadeGeneroNoMes(String mesReferencia);
    AtendimentosComDeficiencia contarAtendimentosComDeficienciaNoMes(String mesReferencia);
    AtendimentosDeImigrantes contarAtendimentosDeImigrantesNoMes(String mesReferencia);
    AtendimentosEgressoPrisional contarAtendimentosEgressoPrisionalNoMes(String mesReferencia);
    AtendimentosEnderecoReferencia contarAtendimentosEnderecoReferenciaNoMes(String mesReferencia);
    AtendimentosDeLgbt contarAtendimentosDeLgbtNoMes(String mesReferencia);
    List<AtendimentosPorLocalDorme> contarAtendimentosPorLocalDormeNoMes(String mesReferencia);
    AtendimentosDeBanho contarAtendimentosDeBanhoNoMes(String mesReferencia);
    AtendimentosDeLavagemRoupa contarAtendimentosDeLavagemRoupaNoMes(String mesReferencia);
    AtendimentosDeRefeicao contarAtendimentosDeRefeicaoNoMes(String mesReferencia);
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
    List<AtendimentosDiaDto> buscarAtendimentosPorDia(LocalDate dia);
}
