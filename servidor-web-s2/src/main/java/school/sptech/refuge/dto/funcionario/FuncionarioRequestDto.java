package school.sptech.refuge.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioRequestDto {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(description = "Nome do usuário")
    private String nome;

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    @Size(min = 11, max = 14)
    private String telefone;

    @NotNull
    @NotBlank
    @Email
    @Schema(description = "Email do usuário")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    @Schema(description = "Senha do usuário")
    private String senha;

    public FuncionarioRequestDto(String nome, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public FuncionarioRequestDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
