package school.sptech.refuge.dto.registro.atendimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class RegistroAtendimentoRequestDto {

    @NotNull
    private LocalDateTime dataRegistro;


    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = LocalDateTime.now();
    }

}
