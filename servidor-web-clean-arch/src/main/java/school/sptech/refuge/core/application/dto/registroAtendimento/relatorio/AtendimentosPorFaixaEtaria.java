package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

public class AtendimentosPorFaixaEtaria {

    private String faixaEtaria;
    private String sexo;
    private Long numeroPessoasAtendidas;

    public AtendimentosPorFaixaEtaria(String faixaEtaria, String sexo, Long numeroPessoasAtendidas) {
        this.faixaEtaria = faixaEtaria;
        this.sexo = sexo;
        this.numeroPessoasAtendidas = numeroPessoasAtendidas;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getNumeroPessoasAtendidas() {
        return numeroPessoasAtendidas;
    }

    public void setNumeroPessoasAtendidas(Long numeroPessoasAtendidas) {
        this.numeroPessoasAtendidas = numeroPessoasAtendidas;
    }
}
