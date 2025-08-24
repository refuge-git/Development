package school.sptech.refuge.core.adapters.dto.tipogenero;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoGeneroRequestDto {

    @NotNull
    @NotBlank
    @Schema(description = "Nome dado ao Gênero", example = "não-binário")
    private String nome;

    @NotNull
    @NotBlank
    @Schema(description = "Descrição do gênero", example = "Pessoa que não se indentifica nem como um homem nem como mulher")
    private String descricao;

    public TipoGeneroRequestDto(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
    }

    public TipoGeneroRequestDto() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
