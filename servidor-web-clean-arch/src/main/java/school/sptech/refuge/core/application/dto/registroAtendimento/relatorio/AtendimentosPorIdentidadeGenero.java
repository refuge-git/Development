package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosPorIdentidadeGenero {

    private String genero;
    private Long numeroPessoasAtendidas;

    public AtendimentosPorIdentidadeGenero() {
    }

    public AtendimentosPorIdentidadeGenero(String genero, Long numeroPessoasAtendidas) {
        this.genero = genero;
        this.numeroPessoasAtendidas = numeroPessoasAtendidas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getNumeroPessoasAtendidas() {
        return numeroPessoasAtendidas;
    }

    public void setNumeroPessoasAtendidas(Long numeroPessoasAtendidas) {
        this.numeroPessoasAtendidas = numeroPessoasAtendidas;
    }
}
