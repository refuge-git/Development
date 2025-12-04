
package school.sptech.refuge.core.application.dto.registroAtendimento;

import java.time.LocalDateTime;

public class UltimoRegistroDto {
    private LocalDateTime dataHora;

    public UltimoRegistroDto() {}

    public UltimoRegistroDto(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
