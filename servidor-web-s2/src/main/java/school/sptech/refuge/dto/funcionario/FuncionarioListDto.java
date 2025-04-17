package school.sptech.refuge.dto.funcionario;


import io.swagger.v3.oas.annotations.media.Schema;

public class FuncionarioListDto {

    @Schema(description = "Id do usuário")
    private Integer id;
    @Schema(description = "Nome do usuário")
    private String nome;
    private String cpf;
    private String telefone;
    @Schema(description = "Email do usuário")
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
