package school.sptech.refuge.dto.beneficiario;

import school.sptech.refuge.dto.FuncionarioBeneficiarioListDto;
import school.sptech.refuge.entity.Funcionario;
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
    private String genero;
    private String raca;
    private String nomeMae;
    private String fotoPerfil;
    private String sisa;
    private String status;
    private LocalDateTime data_ativacao;

    private FuncionarioBeneficiarioListDto funcionario;


    public BeneficarioListDto(Integer id, String nome, LocalDate dtNasc, String cpf, String genero, String raca, String nomeMae, String fotoPerfil, String sisa, String status, LocalDateTime data_ativacao, FuncionarioBeneficiarioListDto funcionario) {
        this.id = id;
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
}
