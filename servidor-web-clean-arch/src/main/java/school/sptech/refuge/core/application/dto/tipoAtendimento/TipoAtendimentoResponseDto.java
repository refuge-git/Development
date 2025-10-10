package school.sptech.refuge.core.application.dto.tipoAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;

import java.time.LocalDateTime;

public class TipoAtendimentoResponseDto {
    @Schema(description = "ID do atendimento retornado", example = "1")
    private Integer id;
    @Schema(description = "Nome de tipo de atendimentos", example = "Refeição")
    private String nome;
    @Schema(description = "Descrição do tipo de atendimento", example = "Distribuição de refeições aos assistidos.")
    private String descricao;
    @Schema(description = "Data de criação desse atendimento", example = "2025-06-07T14:30:00")
    private LocalDateTime dtCriacao;
    @Schema(description = "Funcionário associado ao registro do atendimento")
    private Integer funcionario;

    public TipoAtendimentoResponseDto(Integer id, String nome, String descricao, LocalDateTime dtCriacao, Integer funcionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dtCriacao = dtCriacao;
        this.funcionario = funcionario;
    }

    public TipoAtendimentoResponseDto() {
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

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }
}
