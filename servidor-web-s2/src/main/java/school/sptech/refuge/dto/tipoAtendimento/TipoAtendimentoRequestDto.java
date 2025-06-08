package school.sptech.refuge.dto.tipoAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class TipoAtendimentoRequestDto {
    @Schema(description = "Nome de tipo de atendimentos", example = "Psicólógico")
    private String nome;
    @Schema(description = "Descrição do atendimento realizado", example = "Atendimento com psicólog")
    private String descricao;
    @Schema(description = "Data de criação desse atendimento", example = "2025-06-07T14:30:00")
    private Date dt_criacao;

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

    public Date getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Date dt_criacao) {
        this.dt_criacao = dt_criacao;
    }
}
