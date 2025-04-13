package school.sptech.refuge.entity;

import java.time.LocalDate;

public class TipoAtendimento {

    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dtCriacao;

    public TipoAtendimento(Integer id, String nome, String descricao, LocalDate dtCriacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dtCriacao = dtCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }
}
