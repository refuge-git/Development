package school.sptech.refuge.dto.beneficiario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import school.sptech.refuge.entity.GeneroEnum;

import java.time.LocalDate;

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
    private String raca;

    @NotNull
    @NotBlank
    private String nomeMae;

    private String fotoPerfil;
    private String sisa;



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

    public String getNumeroCartao() {
        return sisa;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.sisa = numeroCartao;
    }

}
