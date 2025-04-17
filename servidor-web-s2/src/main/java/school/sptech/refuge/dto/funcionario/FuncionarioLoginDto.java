package school.sptech.refuge.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;

public class FuncionarioLoginDto {

    @Schema(description = "E-mail do usuário")
    private String email;

    @Schema(description = "Senha do usuário")
    private String senha;

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
