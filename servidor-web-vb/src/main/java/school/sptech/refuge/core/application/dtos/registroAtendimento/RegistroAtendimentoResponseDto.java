package school.sptech.refuge.core.application.dtos.registroAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.refuge.core.application.dtos.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dtos.tipoAtendimento.TipoAtendimentoResponseDto;

import java.time.LocalDateTime;

public class RegistroAtendimentoResponseDto {
    @Schema(description = "Id da entidade retornada ao ser salva", example = "1")
    private Integer id;
    @Schema(description = "Data e hora que aquele registro foi feito", example = "2025-06-07T14:30:00")
    private LocalDateTime dataHora;
    @Schema(description = "Entidade retornada pela relação de tipo de atendimento", example = "1")
    private TipoAtendimentoResponseDto tipoAtendimento;
    @Schema(description = "Entidade retornada pela relação de beneficiario", example = "1")
    private BeneficarioListDto beneficiario;

    public RegistroAtendimentoResponseDto(Integer id, LocalDateTime dataHora, TipoAtendimentoResponseDto tipoAtendimento, BeneficarioListDto beneficiario) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
        this.beneficiario = beneficiario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoAtendimentoResponseDto getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimentoResponseDto tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public BeneficarioListDto getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(BeneficarioListDto beneficiario) {
        this.beneficiario = beneficiario;
    }
}
