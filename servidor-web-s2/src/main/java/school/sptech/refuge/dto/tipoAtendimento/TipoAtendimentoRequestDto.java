package school.sptech.refuge.dto.tipoAtendimento;

import java.util.Date;

public class TipoAtendimentoRequestDto {
    private String nome;
    private String descricao;
    private Date dt_criacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Date dt_criacao) {
        this.dt_criacao = dt_criacao;
    }
}
