package school.sptech.refuge.antes.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TipoAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_atendimento")
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario", referencedColumnName = "id_funcionario")
    private Funcionario funcionario;

    public TipoAtendimento(){
    }


    public TipoAtendimento(Integer id, String nome, String descricao, LocalDateTime dataCriacao, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.funcionario = funcionario;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
