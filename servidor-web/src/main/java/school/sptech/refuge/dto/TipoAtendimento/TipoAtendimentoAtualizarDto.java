package school.sptech.refuge.dto.TipoAtendimento;

import java.time.LocalDate;

public class TipoAtendimentoAtualizarDto {

    private String nome;
    private String descricao;
    private Integer idFuncionario;

    public TipoAtendimentoAtualizarDto(String nome, String descricao, Integer idFuncionario) {
        this.nome = nome;
        this.descricao = descricao;
        this.idFuncionario = idFuncionario;
    }

    public TipoAtendimentoAtualizarDto() {
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

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
