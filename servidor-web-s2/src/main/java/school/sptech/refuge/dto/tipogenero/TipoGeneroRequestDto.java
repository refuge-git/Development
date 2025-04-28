package school.sptech.refuge.dto.tipogenero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoGeneroRequestDto {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
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
