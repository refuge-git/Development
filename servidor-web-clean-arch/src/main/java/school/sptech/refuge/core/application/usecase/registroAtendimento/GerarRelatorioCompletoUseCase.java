package school.sptech.refuge.core.application.usecase.registroAtendimento;

import school.sptech.refuge.core.adapters.PublicarRelatorioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.*;

import java.util.List;

public class GerarRelatorioCompletoUseCase {

    private final RegistroAtendimentoGateway gateway;
    private final PublicarRelatorioGateway publicarRelatorioGateway;

    public GerarRelatorioCompletoUseCase(RegistroAtendimentoGateway gateway, PublicarRelatorioGateway publicarRelatorioGateway) {
        this.gateway = gateway;
        this.publicarRelatorioGateway = publicarRelatorioGateway;
    }

    public RelatorioCompleto execute(String email, String mesReferencia) {
        List<PresencaDia> resultadoPresenca = gateway.contarPresencasPorDiaNoMes(mesReferencia);
        PresencaDiaResponse responsePresenca = new PresencaDiaResponse(email, resultadoPresenca);
        List<AtendimentosPorFaixaEtaria> resultadoIdades = gateway.contarAtendimentosPorFaixaEtaria(mesReferencia);
        List<AtendimentosPorRacaSexo> resultadoRacaSexo = gateway.contarAtendimentosRacaSexoNoMes(mesReferencia);
        List<AtendimentosPorIdentidadeGenero> resultadoIdentidadeGenero = gateway.contarAtendimentosIdentidadeGeneroNoMes(mesReferencia);
        AtendimentosComDeficiencia resultadoComDeficiencia = gateway.contarAtendimentosComDeficienciaNoMes(mesReferencia);
        AtendimentosDeImigrantes resultadoDeImigrantes = gateway.contarAtendimentosDeImigrantesNoMes(mesReferencia);
        AtendimentosEgressoPrisional resultadoEgressoPrisional = gateway.contarAtendimentosEgressoPrisionalNoMes(mesReferencia);
        AtendimentosEnderecoReferencia resultadoEnderecoReferencia = gateway.contarAtendimentosEnderecoReferenciaNoMes(mesReferencia);
        AtendimentosDeLgbt resultadoDeLgbt = gateway.contarAtendimentosDeLgbtNoMes(mesReferencia);
        List<AtendimentosPorLocalDorme> resultadoPorLocalDorme = gateway.contarAtendimentosPorLocalDormeNoMes(mesReferencia);
        AtendimentosDeBanho resultadoDeBanho = gateway.contarAtendimentosDeBanhoNoMes(mesReferencia);
        AtendimentosDeLavagemRoupa resultadoDeLavagemRoupa = gateway.contarAtendimentosDeLavagemRoupaNoMes(mesReferencia);
        AtendimentosDeRefeicao resultadoDeRefeicao = gateway.contarAtendimentosDeRefeicaoNoMes(mesReferencia);


        RelatorioCompleto relatorioCompleto = new RelatorioCompleto(responsePresenca, resultadoIdades, resultadoRacaSexo,
                resultadoIdentidadeGenero, resultadoComDeficiencia, resultadoDeImigrantes, resultadoEgressoPrisional, resultadoEnderecoReferencia,
                resultadoDeLgbt, resultadoPorLocalDorme, resultadoDeBanho, resultadoDeLavagemRoupa,
                resultadoDeRefeicao);
        publicarRelatorioGateway.publicarRelatorioCompleto(relatorioCompleto);
        return relatorioCompleto;
    }
}
