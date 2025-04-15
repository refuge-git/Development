package school.sptech.refuge.dto.beneficiario;

import school.sptech.refuge.entity.GeneroEnum;
import school.sptech.refuge.entity.RacaEnum;
import school.sptech.refuge.entity.StatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BeneficarioListDto {

    private Integer id;
    private String nome;
    private LocalDate dtNasc;
    private String cpf;
    private GeneroEnum generoEnum;
    private RacaEnum raca;
    private String nomeMae;
    private String fotoPerfil;
    private String sisa;
    private StatusEnum statusEnum;
    private LocalDateTime data_ativacao;
    private String nomeFuncionario;


    public BeneficarioListDto(Integer id, String nome, LocalDate dtNasc, String cpf, GeneroEnum generoEnum, RacaEnum raca, String nomeMae, String fotoPerfil, String sisa, StatusEnum statusEnum, LocalDateTime data_ativacao, String nomeFuncionario) {
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
        this.data_ativacao = data_ativacao;
        this.nomeFuncionario = nomeFuncionario;
    }

    public BeneficarioListDto() {
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
        return data_ativacao;
    }

    public void setData_ativacao(LocalDateTime data_ativacao) {
        this.data_ativacao = data_ativacao;
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

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
}
