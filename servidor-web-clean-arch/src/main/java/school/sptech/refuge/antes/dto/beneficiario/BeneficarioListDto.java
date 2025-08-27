package school.sptech.refuge.antes.dto.beneficiario;
import io.swagger.v3.oas.annotations.media.Schema;

import school.sptech.refuge.antes.dto.endereco.EnderecoListDto;
import school.sptech.refuge.antes.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.antes.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.antes.dto.tiposexualidade.TipoSexualidadeListDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "DTO responsável por receber os dados via body")
public class BeneficarioListDto {

    private Integer id;
    @Schema(description = "Nome completo do usuário", example = "Raul Reis")
    private String nomeRegistro;
    @Schema(description = "Nome social do usuário", example = "Roberta Reis")
    private String nomeSocial;
    @Schema(description = "Data de nascimento do usuário", example = "19/08/2004")
    private LocalDate dtNasc;
    @Schema(description = "CPF do usuário", example = "000.000.000.00")
    private String cpf;
    @Schema(description = "Boolean se beneficiário é estrangeiro ou não")
    private Boolean estrangeiro;
    @Schema(description = "Raça do usuário", example = "Branco")
    private String raca;
    @Schema(description = "Sexo do beneficiário", example = "Feminino")
    private String sexo;
    @Schema(description = "Nome completo da mãe do usuário", example = "Carmen Silva")
    private String nomeMae;
    @Schema(description = "Boolean se beneficiário já esteve preso")
    private Boolean egressoPrisional;
    @Schema(description = "Local em que o beneficiário dorme", example = "Casa")
    private String localDorme;
    @Schema(description = "Endereço de foto do usuário")
    private String fotoPerfil;
    @Schema(description = "Número cisar do usuário", example = "12345")
    private String sisa;
    @Schema(description = "Status de atividade do usuário", example = "ATIVO - INATIVO")
    private String status;
    @Schema(description = "Data de ativação do usuário", example = "25/07/2020")
    private LocalDateTime data_ativacao;
    @Schema(description = "Observações sobre o beneficiário")
    private String observacao;

    private FuncionarioListDto funcionario;
    private EnderecoListDto endereco;
    private TipoGeneroListDto tipoGenero;
    private TipoSexualidadeListDto tipoSexualidade;


    public BeneficarioListDto(Integer id, String nomeRegistro, String nomeSocial, LocalDate dtNasc, String cpf, Boolean estrangeiro, String raca, String sexo, String nomeMae, Boolean egressoPrisional, String localDorme, String fotoPerfil, String sisa, String status, LocalDateTime data_ativacao, String observacao, FuncionarioListDto funcionario, EnderecoListDto endereco, TipoGeneroListDto tipoGenero, TipoSexualidadeListDto tipoSexualidade) {
        this.id = id;
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
        this.status = status;
        this.data_ativacao = data_ativacao;
        this.observacao = observacao;
        this.funcionario = funcionario;
        this.endereco = endereco;
        this.tipoGenero = tipoGenero;
        this.tipoSexualidade = tipoSexualidade;
    }

    public BeneficarioListDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
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

    public Boolean getEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(Boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Boolean getEgressoPrisional() {
        return egressoPrisional;
    }

    public void setEgressoPrisional(Boolean egressoPrisional) {
        this.egressoPrisional = egressoPrisional;
    }

    public String getLocalDorme() {
        return localDorme;
    }

    public void setLocalDorme(String localDorme) {
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public FuncionarioListDto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioListDto funcionario) {
        this.funcionario = funcionario;
    }

    public EnderecoListDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoListDto endereco) {
        this.endereco = endereco;
    }

    public TipoGeneroListDto getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(TipoGeneroListDto tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public TipoSexualidadeListDto getTipoSexualidade() {
        return tipoSexualidade;
    }

    public void setTipoSexualidade(TipoSexualidadeListDto tipoSexualidade) {
        this.tipoSexualidade = tipoSexualidade;
    }
}
