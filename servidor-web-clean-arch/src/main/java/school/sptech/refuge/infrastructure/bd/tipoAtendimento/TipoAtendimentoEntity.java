package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import jakarta.persistence.*;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_atendimento")
public class TipoAtendimentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_atendimento")
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario", referencedColumnName = "id_funcionario")
    private FuncionarioEntity funcionario;

    public TipoAtendimentoEntity(){
    }


    public TipoAtendimentoEntity(Integer id, String nome, String descricao, LocalDateTime dataCriacao, FuncionarioEntity funcionarioEntity) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.funcionario = funcionarioEntity;
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

    public FuncionarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioEntity funcionario) {
        this.funcionario = funcionario;
    }
}
