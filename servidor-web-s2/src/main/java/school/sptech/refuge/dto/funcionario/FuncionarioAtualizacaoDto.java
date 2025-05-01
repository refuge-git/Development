package school.sptech.refuge.dto.funcionario;


import io.swagger.v3.oas.annotations.media.Schema;

public class FuncionarioAtualizacaoDto {

    @Schema(description = "Nome completo do funcionário", example = "Marcio Santa da Silva")
    private String nome;
    @Schema(description = "CPF do funcionário", example = "93827345439")
    private String cpf;
    @Schema(description = "Telefone com ddd do funcionário", example = "(11)98724-9812")
    private String telefone;
    @Schema(description = "Email do funcionário", example = "marcio@gmail.com")
    private String email;
    @Schema(description = "Senha de acesso do funcionário", example = "12345678")
    private String senha;

    public FuncionarioAtualizacaoDto(String nome, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public FuncionarioAtualizacaoDto() {
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
