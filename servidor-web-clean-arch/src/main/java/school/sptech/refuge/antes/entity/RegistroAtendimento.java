package school.sptech.refuge.antes.entity;

import jakarta.persistence.*;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;

import java.time.LocalDateTime;

@Entity
public class RegistroAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_atendimento")
    private Integer id;
    private LocalDateTime dataHora;


    // Relacionamentos da tabela auxiliar
    @ManyToOne
    @JoinColumn(name = "fk_tipo", referencedColumnName = "id_tipo_atendimento")
    private TipoAtendimento tipoAtendimento;
    @ManyToOne
    @JoinColumn(name = "fk_beneficiario", referencedColumnName = "id_beneficiario")
    private BeneficiarioEntity beneficiarioEntity;

    public RegistroAtendimento(Integer id, LocalDateTime dataHora, TipoAtendimento tipoAtendimento, BeneficiarioEntity beneficiarioEntity) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
        this.beneficiarioEntity = beneficiarioEntity;
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

    public BeneficiarioEntity getBeneficiario() {
        return beneficiarioEntity;
    }

    public void setBeneficiario(BeneficiarioEntity beneficiarioEntity) {
        this.beneficiarioEntity = beneficiarioEntity;
    }
}
