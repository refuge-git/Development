package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosDeRefeicao {

    private Long numeroAtendimentosRefeicao;

    public AtendimentosDeRefeicao() {
    }

    public AtendimentosDeRefeicao(Long numeroAtendimentosRefeicao) {
        this.numeroAtendimentosRefeicao = numeroAtendimentosRefeicao;
    }

    public Long getNumeroAtendimentosRefeicao() {
        return numeroAtendimentosRefeicao;
    }

    public void setNumeroAtendimentosRefeicao(Long numeroAtendimentosRefeicao) {
        this.numeroAtendimentosRefeicao = numeroAtendimentosRefeicao;
    }
}
