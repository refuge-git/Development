package school.sptech.refuge.dto.tipoAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

public class TipoAtendimentoResponseDto {
    @Schema(description = "ID do atendimento retornado", example = "1")
    private Integer id;
    @Schema(description = "Nome de tipo de atendimentos", example = "Psicólógico")
    private String nome;

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
