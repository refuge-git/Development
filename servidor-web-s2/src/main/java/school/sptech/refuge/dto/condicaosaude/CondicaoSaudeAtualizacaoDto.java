package school.sptech.refuge.dto.condicaosaude;

import io.swagger.v3.oas.annotations.media.Schema;

public class CondicaoSaudeAtualizacaoDto {

    @Schema(description = "Descrição da condição de saúde")
    private String descricao;
    @Schema(description = "Tratamento da condição de saúde")
    private String tratamento;
    @Schema(description = "Observações da condição de saúde")
    private String observacoes;
    @Schema(description = "Id do beneficiário da condição de saúde")
    private Integer idBeneficiario;
    @Schema(description = "Id da categoria da condição de saúde")
    private Integer idCategoria;

    public CondicaoSaudeAtualizacaoDto(String descricao, String tratamento, String observacoes, Integer idBeneficiario, Integer idCategoria) {
        this.descricao = descricao;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.idBeneficiario = idBeneficiario;
        this.idCategoria = idCategoria;
    }

    public CondicaoSaudeAtualizacaoDto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}
