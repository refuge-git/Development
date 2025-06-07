package school.sptech.refuge.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TipoAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_TipoAtendimento;
    private String nome;
    private String descricao;
    private Date dt_criacao;

    @ManyToOne
    private Funcionario funcionario;

    public TipoAtendimento(){
    }

    public TipoAtendimento(Integer id_TipoAtendimento, String nome, String descricao, Date dt_criacao, Funcionario funcionario) {
        this.id_TipoAtendimento = id_TipoAtendimento;
        this.nome = nome;
        this.descricao = descricao;
        this.dt_criacao = dt_criacao;
        this.funcionario = funcionario;
    }

    public Integer getId_TipoAtendimento() {
        return id_TipoAtendimento;
    }

    public void setId_TipoAtendimento(Integer id_TipoAtendimento) {
        this.id_TipoAtendimento = id_TipoAtendimento;
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

    public Date getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Date dt_criacao) {
        this.dt_criacao = dt_criacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
