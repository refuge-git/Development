package school.sptech.refuge.core.adapters.dto.registroAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;

public class RegistroAtendimentoRequestDto {
    private LocalDateTime dataHora;
    @Schema(description = "Id do tipo de atendimento referenciado na relação", example = "1")
    private Integer idTipoAtendimento;
    @Schema(description = "Id do beneficiario referenciado na relação", example = "1")
    private Integer idBeneficiario;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getIdTipoAtendimento() {
        return idTipoAtendimento;
    }

    public void setIdTipoAtendimento(Integer idTipoAtendimento) {
        this.idTipoAtendimento = idTipoAtendimento;
    }

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }
}
