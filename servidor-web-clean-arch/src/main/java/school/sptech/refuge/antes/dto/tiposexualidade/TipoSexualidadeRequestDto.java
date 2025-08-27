package school.sptech.refuge.antes.dto.tiposexualidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoSexualidadeRequestDto {

    @NotNull
    @NotBlank
    @Schema(description = "Nome dado ao Sexualidade", example = "Héterossexual")
    private String nome;

    @NotNull
    @NotBlank
    @Schema(description = "Descrição da sexualidade", example = "Pessoa que se relaciona apenas com pessoas do sexo oposto.")
    private String descricao;

    public TipoSexualidadeRequestDto(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
    }

    public TipoSexualidadeRequestDto() {
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
