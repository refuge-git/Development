package school.sptech.refuge.dto.condicaosaude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.categoria.CategoriaListDto;
import school.sptech.refuge.entity.Categoria;

import java.time.LocalDate;

public class CondicaoSaudeListDto {

    @Schema(description = "Id da condição de saúde")
    private Integer id;
    @Schema(description = "Descrição da condição de saúde")
    private String descricao;
    @Schema(description = "Data de registro da condição de saúde")
    private LocalDate dataRegistro;
    @Schema(description = "Tratamento da condição de saúde")
    private String tratamento;
    @Schema(description = "Observações da condição de saúde")
    private String observacoes;

    private BeneficarioListDto beneficiario;
    @Schema(description = "Categoria da condição de saúde")
    private CategoriaListDto categoria;

    public CondicaoSaudeListDto(Integer id, String descricao, LocalDate dataRegistro, String tratamento, String observacoes, BeneficarioListDto beneficiario, CategoriaListDto categoria) {
        this.id = id;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
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

