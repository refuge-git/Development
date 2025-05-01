package school.sptech.refuge.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class FuncionarioBeneficiarioListDto {

    private Integer id;
    @Schema(description = "Nome completo do funcionário", example = "Gustavo Pereira")
    private String nome;
    @Schema(description = "Telefone do funcionário", example = "(11)98315-8730")
    private String telefone;
    @Schema(description = "Email do funcionário", example = "gustavo@gmail.com")
    private String email;

    public FuncionarioBeneficiarioListDto(Integer id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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
