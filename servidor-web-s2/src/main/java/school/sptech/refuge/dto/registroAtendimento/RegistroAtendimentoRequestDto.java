package school.sptech.refuge.dto.registroAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class RegistroAtendimentoRequestDto {
    private Date data_hora;
    @Schema(description = "Id do tipo de atendimento referenciado na relação", example = "1")
    private Integer idTipoAtendimento;
    @Schema(description = "Id do beneficiario referenciado na relação", example = "1")
    private Integer idBeneficiario;

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
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
