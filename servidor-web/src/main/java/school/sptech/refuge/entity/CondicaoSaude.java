package school.sptech.refuge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class CondicaoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @NotBlank
    private LocalDateTime dataDiagnostico;

    @NotNull
    private String tratamento;

    @NotNull
    private String observacoes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull @NotBlank String descricao) {
        this.descricao = descricao;
    }

    public @NotNull @NotBlank LocalDateTime getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(@NotNull @NotBlank LocalDateTime dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }

    public @NotNull String getTratamento() {
        return tratamento;
    }

    public void setTratamento(@NotNull String tratamento) {
        this.tratamento = tratamento;
    }

    public @NotNull String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(@NotNull String observacoes) {
        this.observacoes = observacoes;
    }
}
