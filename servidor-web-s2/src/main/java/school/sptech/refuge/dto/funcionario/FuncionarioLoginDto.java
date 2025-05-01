package school.sptech.refuge.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;

public class FuncionarioLoginDto {

    @Schema(description = "Email do funcionário", example = "marcio@gmail.com")
    private String email;

    @Schema(description = "Senha de acesso do funcionário", example = "12345678")
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
