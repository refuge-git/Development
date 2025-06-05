package school.sptech.refuge.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoriaAtualizacaoDto {
    @Schema(description = "Nome da categoria")
    private String nome;

    public CategoriaAtualizacaoDto(String nome) {
        this.nome = nome;
    }

    public CategoriaAtualizacaoDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
