package school.sptech.refuge.dto.beneficiario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.entity.TipoGenero;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BeneficiarioRequestDto {

    @NotNull
    @NotBlank
    @Schema(description = "Nome de registro do beneficiário", example = "Carolina Santos")
    private String nomeRegistro;

    @NotNull
    @NotBlank
    @Schema(description = "Nome social do beneficiário", example = "Julia Santos")
    private String nomeSocial;

    @NotNull
    @Past
    @Schema(description = "Data de nascimento do beneficiário", example = "12/09/1997")
    private LocalDate dtNasc;


    @Schema(description = "CPF do beneficiário", example = "32576924590")
    private String cpf;


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

    @Schema(description = "Status atual do beneficiário", example = "ATIVO")
    private String status;

    @CurrentTimestamp
    @Schema(description = "Data de registro do beneficiário", example = "01/05/2025")
    private LocalDateTime data_ativacao;

    @NotNull
    @Schema(description = "ID do funcionário responsável", example = "1")
    private Integer idFuncionario;

    @NotNull
    @Schema(description = "ID do endereço do beneficiário", example = "3")
    private Integer idEndereco;

    @NotNull
    @Schema(description = "ID do tipo de gênero", example = "2")
    private Integer idTipoGenero;


    public BeneficiarioRequestDto(String nomeRegistro, String nomeSocial, LocalDate dtNasc, String cpf, String raca, String nomeMae, String fotoPerfil, String sisa, String status, LocalDateTime data_ativacao, Integer idFuncionario, Integer idEndereco, Integer idTipoGenero) {
        this.nomeRegistro = nomeRegistro;
        this.nomeSocial = nomeSocial;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.raca = raca;
        this.nomeMae = nomeMae;
        this.fotoPerfil = fotoPerfil;
        this.sisa = sisa;
        this.status = status;
        this.data_ativacao = data_ativacao;
        this.idFuncionario = idFuncionario;
        this.idEndereco = idEndereco;
        this.idTipoGenero = idTipoGenero;
    }

    public BeneficiarioRequestDto() {
    }

    public @NotNull @NotBlank String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(@NotNull @NotBlank String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public @NotNull @NotBlank String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(@NotNull @NotBlank String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public @NotNull @Past LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(@NotNull @Past LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public @NotNull @NotBlank String getRaca() {
        return raca;
    }

    public void setRaca(@NotNull @NotBlank String raca) {
        this.raca = raca;
    }

    public @NotNull @NotBlank String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(@NotNull @NotBlank String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getData_ativacao() {
        return data_ativacao;
    }

    public void setData_ativacao(LocalDateTime data_ativacao) {
        this.data_ativacao = data_ativacao;
    }

    public @NotNull Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(@NotNull Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public @NotNull Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(@NotNull Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public @NotNull Integer getIdTipoGenero() {
        return idTipoGenero;
    }

    public void setIdTipoGenero(@NotNull Integer idTipoGenero) {
        this.idTipoGenero = idTipoGenero;
    }
}
