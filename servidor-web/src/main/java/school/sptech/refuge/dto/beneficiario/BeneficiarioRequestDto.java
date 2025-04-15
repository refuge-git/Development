package school.sptech.refuge.dto.beneficiario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import school.sptech.refuge.entity.GeneroEnum;
import school.sptech.refuge.entity.RacaEnum;
import school.sptech.refuge.entity.StatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BeneficiarioRequestDto {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    @Past
    private LocalDate dtNasc;

    @CPF
    private String cpf;
    // Usado para que o valor passado seja a nomenclatura do ENUM e não o seu indentificador numérico (MASCULINO, FEMININO...)

    @NotNull
    @NotBlank
    private GeneroEnum generoEnum;

    @NotNull
    @NotBlank
    private RacaEnum raca;

    @NotNull
    @NotBlank
    private String nomeMae;

    private String fotoPerfil;
    private String sisa;
    private StatusEnum statusEnum;
    private LocalDateTime data_ativacao;
    private Integer idFuncionario;
    private Integer idEndereco;


    public BeneficiarioRequestDto(String nome, LocalDate dtNasc, String cpf, GeneroEnum generoEnum, RacaEnum raca, String nomeMae, String fotoPerfil, String sisa, StatusEnum statusEnum, LocalDateTime data_ativacao, Integer idFuncionario, Integer idEndereco) {
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
        this.idFuncionario = idFuncionario;
        this.idEndereco = idEndereco;
    }

    public BeneficiarioRequestDto() {
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

    public GeneroEnum getGeneroEnum() {
        return generoEnum;
    }

    public void setGeneroEnum(GeneroEnum generoEnum) {
        this.generoEnum = generoEnum;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public LocalDateTime getData_ativacao() {
        return data_ativacao;
    }

    public void setData_ativacao(LocalDateTime data_ativacao) {
        this.data_ativacao = data_ativacao;
    }
}
