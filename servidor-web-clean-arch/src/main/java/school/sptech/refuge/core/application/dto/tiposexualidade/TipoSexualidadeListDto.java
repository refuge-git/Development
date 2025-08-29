package school.sptech.refuge.core.application.dto.tiposexualidade;

import io.swagger.v3.oas.annotations.media.Schema;

public class TipoSexualidadeListDto {
    private Integer id;
    @Schema(description = "Nome dado ao Sexualidade", example = "Héterossexual")
    private String nome;
    @Schema(description = "Descrição da sexualidade", example = "Pessoa que se relaciona apenas com pessoas do sexo oposto.")
    private String descricao;

    public TipoSexualidadeListDto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoSexualidadeListDto() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
