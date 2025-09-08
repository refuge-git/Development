package school.sptech.refuge.dto.registroAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;

public class RegistroAtendimentoRequestDto {
    @Schema(description = "Id do tipo de atendimento referenciado na relação", example = "1")
    private Integer idTipoAtendimento;
    @Schema(description = "Id do beneficiario referenciado na relação", example = "1")
    private Integer idBeneficiario;

    public LocalDateTime getDataHora() {
        System.out.println("Point 2!");
        return LocalDateTime.now();
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
