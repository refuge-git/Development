package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosComDeficiencia {

    private Long pessoasComDeficiencia;

    public AtendimentosComDeficiencia() {
    }

    public AtendimentosComDeficiencia(Long pessoasComDeficiencia) {
        this.pessoasComDeficiencia = pessoasComDeficiencia;
    }

    public Long getPessoasComDeficiencia() {
        return pessoasComDeficiencia;
    }

    public void setPessoasComDeficiencia(Long pessoasComDeficiencia) {
        this.pessoasComDeficiencia = pessoasComDeficiencia;
    }
}
