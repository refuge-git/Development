package school.sptech.refuge.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dtNasc;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private GeneroEnum generoEnum;

    @Enumerated(EnumType.STRING)
    private RacaEnum raca;
    private String nomeMae;
    private String fotoPerfil;
    private String sisa;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;
    private LocalDateTime dataAtivacao;


    @ManyToOne
    @JoinColumn(name = "fk_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    public Beneficiario(Integer id, String nome, LocalDate dtNasc, String cpf, GeneroEnum generoEnum, RacaEnum raca, String nomeMae, String fotoPerfil, String sisa, StatusEnum statusEnum, LocalDateTime dataAtivacao, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.generoEnum = generoEnum;
        this.raca = raca;
        this.nomeMae = nomeMae;
        this.fotoPerfil = fotoPerfil;
        this.sisa = sisa;
        this.statusEnum = statusEnum;
        this.dataAtivacao = dataAtivacao;
        this.funcionario = funcionario;
    }

    public Beneficiario() {
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

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public GeneroEnum getGenero() {
        return generoEnum;
    }

    public void setGenero(GeneroEnum generoEnum) {
        this.generoEnum = generoEnum;
    }

    public RacaEnum getRaca() {
        return raca;
    }

    public void setRaca(RacaEnum raca) {
        this.raca = raca;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNumeroCartao() {
        return sisa;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.sisa = numeroCartao;
    }

    public StatusEnum getStatus() {
        return statusEnum;
    }

    public void setStatus(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public LocalDateTime getData_ativacao() {
        return dataAtivacao;
    }

    public void setData_ativacao(LocalDateTime data_ativacao) {
        this.dataAtivacao = data_ativacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public GeneroEnum getGeneroEnum() {
        return generoEnum;
    }

    public void setGeneroEnum(GeneroEnum generoEnum) {
        this.generoEnum = generoEnum;
    }


}
