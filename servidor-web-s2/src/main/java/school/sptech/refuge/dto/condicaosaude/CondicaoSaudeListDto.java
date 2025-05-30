package school.sptech.refuge.dto.condicaosaude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CondicaoSaudeListDto {

    @Schema(description = "Id da condição de saúde")
    private Integer id;
    @Schema(description = "Descrição da condição de saúde")
    private String descricao;
    @Schema(description = "Data de registro da condição de saúde")
    private LocalDate dataRegistro;
    @Schema(description = "Categoria da condição de saúde")
    private String categoria;
    @Schema(description = "Tratamento da condição de saúde")
    private String tratamento;
    @Schema(description = "Observações da condição de saúde")
    private String observacoes;

    public CondicaoSaudeListDto(Integer id, String descricao, LocalDate dataRegistro, String categoria, String tratamento, String observacoes) {
        this.id = id;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.categoria = categoria;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
    }

    public CondicaoSaudeListDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}

