package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosPorRacaSexo {

    private String sexo;
    private String raca;
    private Long numeroPessoasAtendidas;

    public AtendimentosPorRacaSexo() {
    }

    public AtendimentosPorRacaSexo(String sexo, String raca, Long numeroPessoasAtendidas) {
        this.sexo = sexo;
        this.raca = raca;
        this.numeroPessoasAtendidas = numeroPessoasAtendidas;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Long getNumeroPessoasAtendidas() {
        return numeroPessoasAtendidas;
    }

    public void setNumeroPessoasAtendidas(Long numeroPessoasAtendidas) {
        this.numeroPessoasAtendidas = numeroPessoasAtendidas;
    }
}
