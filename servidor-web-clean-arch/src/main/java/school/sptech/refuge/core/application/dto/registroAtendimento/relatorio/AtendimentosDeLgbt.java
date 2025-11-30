package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosDeLgbt {

    private Long numeroPessoasLgbtAtendidas;

    public AtendimentosDeLgbt() {
    }

    public AtendimentosDeLgbt(Long numeroPessoasLgbtAtendidas) {
        this.numeroPessoasLgbtAtendidas = numeroPessoasLgbtAtendidas;
    }

    public Long getNumeroPessoasLgbtAtendidas() {
        return numeroPessoasLgbtAtendidas;
    }

    public void setNumeroPessoasLgbtAtendidas(Long numeroPessoasLgbtAtendidas) {
        this.numeroPessoasLgbtAtendidas = numeroPessoasLgbtAtendidas;
    }
}
