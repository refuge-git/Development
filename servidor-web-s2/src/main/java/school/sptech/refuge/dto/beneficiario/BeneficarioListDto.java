package school.sptech.refuge.dto.beneficiario;
import io.swagger.v3.oas.annotations.media.Schema;

import school.sptech.refuge.dto.FuncionarioBeneficiarioListDto;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.entity.TipoGenero;

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
    @Schema(description = "Raça do usuário", example = "Branco")
    private String raca;
    @Schema(description = "Nome completo da mãe do usuário", example = "Carmen Silva")
    private String nomeMae;
    @Schema(description = "Endereço de foto do usuário")
    private String fotoPerfil;
    @Schema(description = "Número cisar do usuário", example = "12345")
    private String sisa;
    @Schema(description = "Status de atividade do usuário", example = "ATIVO - INATIVO")
    private String status;
    @Schema(description = "Data de ativação do usuário", example = "25/07/2020")
    private LocalDateTime data_ativacao;

    private FuncionarioBeneficiarioListDto funcionario;
    private EnderecoListDto endereco;
    private TipoGeneroListDto tipoGenero;


    public BeneficarioListDto(Integer id, String nomeRegistro, String nomeSocial, LocalDate dtNasc, String cpf, String raca, String nomeMae, String fotoPerfil, String sisa, String status, LocalDateTime data_ativacao, FuncionarioBeneficiarioListDto funcionario, EnderecoListDto endereco, TipoGeneroListDto tipoGenero) {
        this.id = id;
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
        this.funcionario = funcionario;
        this.endereco = endereco;
        this.tipoGenero = tipoGenero;
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
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

    public FuncionarioBeneficiarioListDto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioBeneficiarioListDto funcionario) {
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
}
