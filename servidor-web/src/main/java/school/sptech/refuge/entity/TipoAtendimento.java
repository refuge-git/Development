package school.sptech.refuge.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TipoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dtCriacao;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    public TipoAtendimento(Integer id, String nome, String descricao, LocalDate dtCriacao, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dtCriacao = LocalDate.now();
        this.funcionario = funcionario;
    }

    public TipoAtendimento(Integer id, String nome, String descricao, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.funcionario = funcionario;
    }

    public TipoAtendimento() {
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
