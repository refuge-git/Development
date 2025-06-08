package school.sptech.refuge.dto.registroAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.entity.Beneficiario;

import java.util.Date;

public class RegistroAtendimentoResponseDto {
    @Schema(description = "Id da entidade retornada ao ser salva", example = "1")
    private Integer id;
    @Schema(description = "Data e hora que aquele registro foi feito", example = "2025-06-07T14:30:00")
    private Date data_hora;
    @Schema(description = "Entidade retornada pela relação de tipo de atendimento", example = "1")
    private TipoAtendimentoResponseDto tipoAtendimento;
    @Schema(description = "Entidade retornada pela relação de beneficiario", example = "1")
    private Beneficiario beneficiario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public TipoAtendimentoResponseDto getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimentoResponseDto tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
}
