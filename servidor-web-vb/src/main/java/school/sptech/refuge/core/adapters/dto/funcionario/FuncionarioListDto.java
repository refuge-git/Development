package school.sptech.refuge.core.adapters.dto.funcionario;


import io.swagger.v3.oas.annotations.media.Schema;

public class FuncionarioListDto {

    @Schema(description = "Id do usuário")
    private Integer id;
    @Schema(description = "Nome completo do funcionário", example = "Marcio Santa da Silva")
    private String nome;
    @Schema(description = "CPF do funcionário", example = "93827345439")
    private String cpf;
    @Schema(description = "Telefone com ddd do funcionário", example = "(11)98724-9812")
    private String telefone;
    @Schema(description = "Email do funcionário", example = "marcio@gmail.com")
    private String email;

    public FuncionarioListDto(Integer id, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public FuncionarioListDto() {
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
}
