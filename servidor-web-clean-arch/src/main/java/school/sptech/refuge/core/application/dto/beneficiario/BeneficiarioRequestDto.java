package school.sptech.refuge.core.application.dto.beneficiario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BeneficiarioRequestDto {

    @NotNull
    @NotBlank
    @Schema(description = "Nome de registro do beneficiário", example = "Carolina Santos")
    private String nomeRegistro;


    @Schema(description = "Nome social do beneficiário", example = "Julia Santos")
    private String nomeSocial;

    @NotNull
    @Past
    @Schema(description = "Data de nascimento do beneficiário", example = "12/09/1997")
    private LocalDate dtNasc;

    @NotNull
    @Schema(description = "CPF do beneficiário", example = "32576924590")
    private String cpf;

    @NotNull
    @Schema(description = "Boolean se beneficiário é estrangeiro ou não")
    private Boolean estrangeiro;

    @NotNull
    @NotBlank
    @Schema(description = "Raça do beneficiário", example = "PARDO")
    private String raca;

    @NotNull
    @NotBlank
    @Schema(description = "Sexo do beneficiário", example = "FEMININO")
    private String sexo;

    @NotNull
    @NotBlank
    @Schema(description = "Nome da mãe do beneficiário", example = "Maria de Lurdes")
    private String nomeMae;

    @NotNull
    @Schema(description = "Boolean se beneficiário já esteve preso")
    private Boolean egressoPrisional;

    @NotNull
    @NotBlank
    @Schema(description = "Local em que o beneficiário dorme", example = "CASA")
    private String localDorme;

    @Schema(description = "Endereço de foto do beneficiário", example = "carol.jpeg")
    private String fotoPerfil;


    @Schema(description = "Número de indentificação sisar do beneficiário", example = "92817")
    private String sisa;

    @NotNull
    @Schema(description = "Observações sobre o beneficiário")
    private String observacao;

    @NotNull
    @Schema(description = "ID do funcionário responsável", example = "1")
    private Integer idFuncionario;


    @Schema(description = "ID do endereço do beneficiário", example = "3")
    private Integer idEndereco;

    @NotNull
    @Schema(description = "ID do tipo de gênero", example = "2")
    private Integer idTipoGenero;

    @NotNull
    @Schema(description = "ID do tipo de sexualidade", example = "2")
    private Integer idTipoSexualidade;

    @Schema(description = "Imagem salva no bucket", example = "2")
    private String imagem;


    public BeneficiarioRequestDto(String nomeRegistro, String nomeSocial, LocalDate dtNasc, String cpf, Boolean estrangeiro, String raca, String sexo, String nomeMae, Boolean egressoPrisional, String localDorme, String fotoPerfil, String sisa, String observacao, Integer idFuncionario, Integer idEndereco, Integer idTipoGenero, Integer idTipoSexualidade, String imagem) {
        this.nomeRegistro = nomeRegistro;
        this.nomeSocial = nomeSocial;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.estrangeiro = estrangeiro;
        this.raca = raca;
        this.sexo = sexo;
        this.nomeMae = nomeMae;
        this.egressoPrisional = egressoPrisional;
        this.localDorme = localDorme;
        this.fotoPerfil = fotoPerfil;
        this.sisa = sisa;
        this.observacao = observacao;
        this.idFuncionario = idFuncionario;
        this.idEndereco = idEndereco;
        this.idTipoGenero = idTipoGenero;
        this.idTipoSexualidade = idTipoSexualidade;
        this.imagem = imagem;
    }

    public BeneficiarioRequestDto() {
    }

    public @NotNull @NotBlank String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public String getNomeSocial() {
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

    public @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull String cpf) {
        this.cpf = cpf;
    }

    public @NotNull Boolean getEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(@NotNull Boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public @NotNull @NotBlank String getRaca() {
        return raca;
    }

    public void setRaca(@NotNull @NotBlank String raca) {
        this.raca = raca;
    }

    public @NotNull @NotBlank String getSexo() {
        return sexo;
    }

    public void setSexo(@NotNull @NotBlank String sexo) {
        this.sexo = sexo;
    }

    public @NotNull @NotBlank String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(@NotNull @NotBlank String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public @NotNull Boolean getEgressoPrisional() {
        return egressoPrisional;
    }

    public void setEgressoPrisional(@NotNull Boolean egressoPrisional) {
        this.egressoPrisional = egressoPrisional;
    }

    public @NotNull @NotBlank String getLocalDorme() {
        return localDorme;
    }

    public void setLocalDorme(@NotNull @NotBlank String localDorme) {
        this.localDorme = localDorme;
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

    public @NotNull String getObservacao() {
        return observacao;
    }

    public void setObservacao(@NotNull String observacao) {
        this.observacao = observacao;
    }

    public @NotNull Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdEndereco() {
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

    public @NotNull Integer getIdTipoSexualidade() {
        return idTipoSexualidade;
    }

    public void setIdTipoSexualidade(@NotNull Integer idTipoSexualidade) {
        this.idTipoSexualidade = idTipoSexualidade;
    }

    public void setImagem(String imagem){
        this.imagem = imagem;
    }

    public  String getImagem(){
        return imagem;
    }
}
