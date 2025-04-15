package school.sptech.refuge.dto.TipoAtendimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class TipoAtendimentoRequestDto {

    @NotNull
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @PastOrPresent
    private LocalDate dtCriacao;

    @NotNull
    private Integer idFuncionario;

    public TipoAtendimentoRequestDto(String nome, String descricao, LocalDate dtCriacao, Integer idFuncionario) {
        this.nome = nome;
        this.descricao = descricao;
        this.dtCriacao = dtCriacao;
        this.idFuncionario = idFuncionario;
    }

    public TipoAtendimentoRequestDto() {
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

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }


    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

}
