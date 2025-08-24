package school.sptech.refuge.core.adapters.dto.condicaosaude;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.refuge.core.adapters.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.adapters.dto.categoria.CategoriaListDto;

import java.time.LocalDateTime;

public class CondicaoSaudeListDto {

    @Schema(description = "Id da condição de saúde")
    private Integer id;
    @Schema(description = "Diagnóstico da condição de saúde")
    private String diagnostico;
    @Schema(description = "Descrição da condição de saúde")
    private String descricao;
    @Schema(description = "Data de registro da condição de saúde")
    private LocalDateTime dataRegistro;
    @Schema(description = "Data de atualização da condição de saúde")
    private LocalDateTime dataAtualizacao;
    @Schema(description = "Tratamento da condição de saúde")
    private String tratamento;
    @Schema(description = "Observações da condição de saúde")
    private String observacoes;

    private BeneficarioListDto beneficiario;
    @Schema(description = "Categoria da condição de saúde")
    private CategoriaListDto categoria;

    public CondicaoSaudeListDto(Integer id, String diagnostico, String descricao, LocalDateTime dataRegistro, LocalDateTime dataAtualizacao, String tratamento, String observacoes, BeneficarioListDto beneficiario, CategoriaListDto categoria) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.dataAtualizacao = dataAtualizacao;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.beneficiario = beneficiario;
        this.categoria = categoria;
    }

    public CondicaoSaudeListDto() {
    }

    /*public CondicaoSaudeListDto(Integer id, String descricao, LocalDate dataRegistro, Categoria categoria, String tratamento, String observacoes) {
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnostico() { return diagnostico; }

    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
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

    public BeneficarioListDto getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(BeneficarioListDto beneficiario) {
        this.beneficiario = beneficiario;
    }

    public CategoriaListDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaListDto categoria) {
        this.categoria = categoria;
    }
}

