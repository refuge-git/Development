package school.sptech.refuge.core.application.dto.tipoAtendimento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;

public class TipoAtendimentoListDto {
    @Schema(description = "Id do tipo de atendimento")
    private Integer id;
    @Schema(description = "Nome do tipo de atendimento", example = "Banho")
    private String nome;
    @Schema(description = "Descrição do tipo de atendimento", example = "Banho que é oferecido ao beneficiário")
    private String descricao;
    @Schema(description = "Data e hora que foi realizado o tipo atendimento oferecido", example = "23/03/2005 12:34:23")
    private LocalDateTime dataAtendimento;


}
