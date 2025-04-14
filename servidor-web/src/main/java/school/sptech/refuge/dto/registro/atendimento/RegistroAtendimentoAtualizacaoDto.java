package school.sptech.refuge.dto.registro.atendimento;

import java.time.LocalDateTime;

public class RegistroAtendimentoAtualizacaoDto {

    private Integer id;
    private LocalDateTime dataRegistro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = LocalDateTime.now();
    }

}
