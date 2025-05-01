package school.sptech.refuge.dto.beneficiario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.entity.GeneroEnum;
import school.sptech.refuge.entity.RacaEnum;
import school.sptech.refuge.entity.StatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BeneficiarioAtualizacaoDto {

    @Schema(description = "Nome completo do usuário", example = "Raul Reis")
    private String nome;
    @Schema(description = "Data de nascimento do usuário", example = "19/08/2004")
    private LocalDate dtNasc;
    @Schema(description = "CPF do usuário", example = "000.000.000.00")
    private String cpf;
    @Schema(description = "Gênero do usuário", example = "Masculino")
    private GeneroEnum generoEnum;
    @Schema(description = "Raça do usuário", example = "Branco")
    private RacaEnum raca;
    @Schema(description = "Nome completo da mãe do usuário", example = "Carmen Silva")
    private String nomeMae;
    @Schema(description = "Endereço de foto do usuário")
    private String fotoPerfil;
    @Schema(description = "Número cisar do usuário", example = "12345")
    private String sisa;
    @Schema(description = "Status de atividade do usuário", example = "ATIVO - INATIVO")
    private StatusEnum statusEnum;
    @Schema(description = "Data de ativação do usuário", example = "25/07/2020")
    private LocalDateTime data_ativacao;
    @Schema(description = "FK que relaciona o endereço ao beneficiario", example = "1")
    private Integer idEndereco;
    @Schema(description = "Entidade que relaciona o funcionario ao beneficiario", example = "Entidade funcionario")
    private Funcionario funcionario;

    public BeneficiarioAtualizacaoDto(String nome, LocalDate dtNasc, String cpf, GeneroEnum generoEnum, RacaEnum raca, String nomeMae, String fotoPerfil, String sisa, StatusEnum statusEnum, LocalDateTime data_ativacao, Integer idEndereco, Funcionario funcionario) {
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
        this.idEndereco = idEndereco;
        this.funcionario = funcionario;
    }

    public BeneficiarioAtualizacaoDto() {
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
