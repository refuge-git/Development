package school.sptech.refuge.core.domain.registroAtendimento;

import jakarta.persistence.*;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.time.LocalDateTime;

public class RegistroAtendimento {
    private Integer id;
    private LocalDateTime dataHora;
    private TipoAtendimento tipoAtendimento;
    private Beneficiario beneficiario;

    public RegistroAtendimento(Integer id, LocalDateTime dataHora, TipoAtendimento tipoAtendimento, Beneficiario beneficiario) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
        this.beneficiario = beneficiario;
    }

    public RegistroAtendimento() {
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

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
}
