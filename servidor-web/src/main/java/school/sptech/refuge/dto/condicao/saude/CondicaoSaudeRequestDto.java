package school.sptech.refuge.dto.condicao.saude;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDateTime;

public class CondicaoSaudeRequestDto {

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @NotBlank
    @Past(message = "A data do diagnóstico deve estar no passado")
    private LocalDateTime dataDiagnostico;

    @NotBlank
    private String tratamento;

    @NotBlank
    private String observacoes;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(LocalDateTime dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
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
