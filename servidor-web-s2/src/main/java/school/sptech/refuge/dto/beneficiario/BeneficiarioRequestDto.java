package school.sptech.refuge.dto.beneficiario;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Past
    @Schema(description = "Data de nascimento do beneficiário", example = "12/09/1997")
    private LocalDate dtNasc;

    @CPF
    @Schema(description = "CPF do beneficiário", example = "32576924590")
    private String cpf;

    @NotNull
    @NotBlank
    @Schema(description = "Gênero do beneficiário", example = "MULHER_TRANS")
    private String genero;

    @NotNull
    @NotBlank
    @Schema(description = "Raça do beneficiário", example = "pardo")
    private String raca;

    @NotNull
    @NotBlank
    @Schema(description = "Nome da mãe do beneficiário", example = "Maria de Lurdes")
    private String nomeMae;

    @Schema(description = "Endereço de foto do beneficiário", example = "ronaldo.jpeg")
    private String fotoPerfil;

    @Schema(description = "Número de indentificação sisar do beneficiário", example = "92817")
    private String sisa;

    @Schema(description = "Status atual do beneficiário", example = "ATIVO - INATIVO")
    private String status;

    @CurrentTimestamp
    @Schema(description = "Data de registro do beneficiário", example = "01/05/2025")
    private LocalDateTime data_ativacao;

    @Schema(description = "Entidade que relaciona o funcionario ao beneficiario", example = "Entidade funcionario")
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
