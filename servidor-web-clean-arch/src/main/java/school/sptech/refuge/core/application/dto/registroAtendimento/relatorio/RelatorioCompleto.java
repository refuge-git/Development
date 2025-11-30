package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

import java.util.List;

public class RelatorioCompleto {

    private PresencaDiaResponse presencaDiaResponses;
    private List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias;
    private List<AtendimentosPorRacaSexo> atendimentosPorRacaSexos;
    private List<AtendimentosPorIdentidadeGenero> atendimentosPorIdentidadeGeneros;
    private AtendimentosComDeficiencia atendimentosComDeficiencia;
    private AtendimentosDeImigrantes atendimentosDeImigrantes;
    private AtendimentosEgressoPrisional atendimentosEgressoPrisional;
    private AtendimentosEnderecoReferencia atendimentosEnderecoReferencia;
    private AtendimentosDeLgbt atendimentosDeLgbt;
    private List<AtendimentosPorLocalDorme> atendimentosPorLocalDorme;
    private AtendimentosDeBanho atendimentosDeBanho;
    private AtendimentosDeLavagemRoupa atendimentosDeLavagemRoupa;
    private AtendimentosDeRefeicao atendimentosDeRefeicao;

    public RelatorioCompleto(PresencaDiaResponse presencaDiaResponses,
                             List<AtendimentosPorFaixaEtaria> atendimentosPorFaixaEtarias,
                             List<AtendimentosPorRacaSexo> atendimentosPorRacaSexos,
                             List<AtendimentosPorIdentidadeGenero> atendimentosPorIdentidadeGeneros,
                             AtendimentosComDeficiencia atendimentosComDeficiencia,
                             AtendimentosDeImigrantes atendimentosDeImigrantes,
                             AtendimentosEgressoPrisional atendimentosEgressoPrisional,
                             AtendimentosEnderecoReferencia atendimentosEnderecoReferencia,
                             AtendimentosDeLgbt atendimentosDeLgbt,
                             List<AtendimentosPorLocalDorme> atendimentosPorLocalDorme,
                             AtendimentosDeBanho atendimentosDeBanho,
                             AtendimentosDeLavagemRoupa atendimentosDeLavagemRoupa,
                             AtendimentosDeRefeicao atendimentosDeRefeicao) {
        this.presencaDiaResponses = presencaDiaResponses;
        this.atendimentosPorFaixaEtarias = atendimentosPorFaixaEtarias;
        this.atendimentosPorRacaSexos = atendimentosPorRacaSexos;
        this.atendimentosPorIdentidadeGeneros = atendimentosPorIdentidadeGeneros;
        this.atendimentosComDeficiencia = atendimentosComDeficiencia;
        this.atendimentosDeImigrantes = atendimentosDeImigrantes;
        this.atendimentosEgressoPrisional = atendimentosEgressoPrisional;
        this.atendimentosEnderecoReferencia = atendimentosEnderecoReferencia;
        this.atendimentosDeLgbt = atendimentosDeLgbt;
        this.atendimentosPorLocalDorme = atendimentosPorLocalDorme;
        this.atendimentosDeBanho = atendimentosDeBanho;
        this.atendimentosDeLavagemRoupa = atendimentosDeLavagemRoupa;
        this.atendimentosDeRefeicao = atendimentosDeRefeicao;
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

    public List<AtendimentosPorIdentidadeGenero> getAtendimentosPorIdentidadeGeneros() {
        return atendimentosPorIdentidadeGeneros;
    }

    public void setAtendimentosPorIdentidadeGeneros(List<AtendimentosPorIdentidadeGenero> atendimentosPorIdentidadeGeneros) {
        this.atendimentosPorIdentidadeGeneros = atendimentosPorIdentidadeGeneros;
    }

    public AtendimentosComDeficiencia getAtendimentosComDeficiencia() {
        return atendimentosComDeficiencia;
    }

    public void setAtendimentosComDeficiencia(AtendimentosComDeficiencia atendimentosComDeficiencia) {
        this.atendimentosComDeficiencia = atendimentosComDeficiencia;
    }

    public AtendimentosDeImigrantes getAtendimentosDeImigrantes() {
        return atendimentosDeImigrantes;
    }

    public void setAtendimentosDeImigrantes(AtendimentosDeImigrantes atendimentosDeImigrantes) {
        this.atendimentosDeImigrantes = atendimentosDeImigrantes;
    }

    public AtendimentosEgressoPrisional getAtendimentosEgressoPrisional() {
        return atendimentosEgressoPrisional;
    }

    public void setAtendimentosEgressoPrisional(AtendimentosEgressoPrisional atendimentosEgressoPrisional) {
        this.atendimentosEgressoPrisional = atendimentosEgressoPrisional;
    }

    public AtendimentosEnderecoReferencia getAtendimentosEnderecoReferencia() {
        return atendimentosEnderecoReferencia;
    }

    public void setAtendimentosEnderecoReferencia(AtendimentosEnderecoReferencia atendimentosEnderecoReferencia) {
        this.atendimentosEnderecoReferencia = atendimentosEnderecoReferencia;
    }

    public AtendimentosDeLgbt getAtendimentosDeLgbt() {
        return atendimentosDeLgbt;
    }

    public void setAtendimentosDeLgbt(AtendimentosDeLgbt atendimentosDeLgbt) {
        this.atendimentosDeLgbt = atendimentosDeLgbt;
    }

    public List<AtendimentosPorLocalDorme> getAtendimentosPorLocalDorme() {
        return atendimentosPorLocalDorme;
    }

    public void setAtendimentosPorLocalDorme(List<AtendimentosPorLocalDorme> atendimentosPorLocalDorme) {
        this.atendimentosPorLocalDorme = atendimentosPorLocalDorme;
    }

    public AtendimentosDeBanho getAtendimentosDeBanho() {
        return atendimentosDeBanho;
    }

    public void setAtendimentosDeBanho(AtendimentosDeBanho atendimentosDeBanho) {
        this.atendimentosDeBanho = atendimentosDeBanho;
    }

    public AtendimentosDeLavagemRoupa getAtendimentosDeLavagemRoupa() {
        return atendimentosDeLavagemRoupa;
    }

    public void setAtendimentosDeLavagemRoupa(AtendimentosDeLavagemRoupa atendimentosDeLavagemRoupa) {
        this.atendimentosDeLavagemRoupa = atendimentosDeLavagemRoupa;
    }

    public AtendimentosDeRefeicao getAtendimentosDeRefeicao() {
        return atendimentosDeRefeicao;
    }

    public void setAtendimentosDeRefeicao(AtendimentosDeRefeicao atendimentosDeRefeicao) {
        this.atendimentosDeRefeicao = atendimentosDeRefeicao;
    }
}
