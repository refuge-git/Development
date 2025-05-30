package school.sptech.refuge.dto.condicaosaude;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class CondicaoSaudeRequestDto {

    @Schema(description = "Descrição da condição de saúde")
    private String descricao;
    @Schema(description = "Data de registro da condição de saúde")
    private LocalDate dataRegistro;
    @Schema(description = "Tratamento da condição de saúde")
    private String tratamento;
    @Schema(description = "Observações da condição de saúde")
    private String observacoes;
    @Schema(description = "Beneficiario que tem a condição de saúde")
    private Integer idBeneficiario;
    @Schema(description = "Categoria da condição de saúde")
    private Integer idCategoria;

    public CondicaoSaudeRequestDto() {
    }

    public CondicaoSaudeRequestDto(String descricao, LocalDate dataRegistro, String tratamento, String observacoes, Integer idBeneficiario, Integer idCategoria) {
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.idBeneficiario = idBeneficiario;
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
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
