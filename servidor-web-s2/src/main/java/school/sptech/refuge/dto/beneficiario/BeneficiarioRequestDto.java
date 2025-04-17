package school.sptech.refuge.dto.beneficiario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import school.sptech.refuge.entity.Funcionario;
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

    @NotNull
    @NotBlank
    private String genero;

    @NotNull
    @NotBlank
    private String raca;

    @NotNull
    @NotBlank
    private String nomeMae;

    private String fotoPerfil;
    private String sisa;
    private String status;

    @CurrentTimestamp
    private LocalDateTime data_ativacao;

    private Funcionario funcionario;


    public BeneficiarioRequestDto(String nome, LocalDate dtNasc, String cpf, String genero, String raca, String nomeMae, String fotoPerfil, String sisa, String status, LocalDateTime data_ativacao, Funcionario funcionario) {
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.genero = genero;
        this.raca = raca;
        this.nomeMae = nomeMae;
        this.fotoPerfil = fotoPerfil;
        this.sisa = sisa;
        this.status = status;
        this.data_ativacao = data_ativacao;
        this.funcionario = funcionario;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getData_ativacao() {
        return data_ativacao;
    }

    public void setData_ativacao(LocalDateTime data_ativacao) {
        this.data_ativacao = data_ativacao;
    }

    public String getStatus() {
        return status;
    }
}
