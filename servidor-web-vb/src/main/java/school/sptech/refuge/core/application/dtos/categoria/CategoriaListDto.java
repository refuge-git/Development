package school.sptech.refuge.core.application.dtos.categoria;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoriaListDto {
    @Schema(description = "Id da categoria")
    private Integer id;
    @Schema(description = "Nome da categoria")
    private String nome;

    public CategoriaListDto() {
    }

    public CategoriaListDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
