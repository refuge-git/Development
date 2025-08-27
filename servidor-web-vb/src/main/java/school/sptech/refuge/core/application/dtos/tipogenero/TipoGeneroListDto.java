package school.sptech.refuge.core.application.dtos.tipogenero;

import io.swagger.v3.oas.annotations.media.Schema;

public class TipoGeneroListDto {

    private Integer id;
    @Schema(description = "Nome dado ao Gênero", example = "não-binário")
    private String nome;
    @Schema(description = "Descrição do gênero", example = "Pessoa que não se indentifica nem como um homem nem como mulher")
    private String descricao;

    public TipoGeneroListDto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoGeneroListDto() {
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
