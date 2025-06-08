package school.sptech.refuge.dto.tipoAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;

public class TipoAtendimentoRequestDto {
    @Schema(description = "Nome de tipo de atendimentos", example = "Psicólógico")
    private String nome;
    @Schema(description = "Descrição do atendimento realizado", example = "Atendimento com psicólog")
    private String descricao;
    @Schema(description = "Data de criação desse atendimento", example = "2025-06-07T14:30:00")
    private LocalDateTime dtCriacao;
    @Schema(description = "Id do funcionário que criou o tipo de atendimento", example = "2")
    private Integer idFuncionario;

    public TipoAtendimentoRequestDto(String nome, String descricao, LocalDateTime dtCriacao, Integer idFuncionario) {
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

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
