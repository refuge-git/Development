package school.sptech.refuge.dto.tipogenero;

public class TipoGeneroListDto {

    private Integer id;
    private String nome;
    private String descricao;

    public TipoGeneroListDto(String descricao, Integer id, String nome) {
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
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
