package school.sptech.refuge.antes.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoriaRequestDto {
    @Schema(description = "Nome da categoria")
    private String nome;

    public CategoriaRequestDto(String nome) {
        this.nome = nome;
    }

    public CategoriaRequestDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
