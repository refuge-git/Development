package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import jakarta.persistence.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_atendimento")
public class RegistroAtendimentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_atendimento")
    private Integer id;
    private LocalDateTime dataHora;


    // Relacionamentos da tabela auxiliar
    @ManyToOne
    @JoinColumn(name = "fk_tipo", referencedColumnName = "id_tipo_atendimento")
    private TipoAtendimentoEntity tipoAtendimento;
    @ManyToOne
    @JoinColumn(name = "fk_beneficiario", referencedColumnName = "id_beneficiario")
    private BeneficiarioEntity beneficiario;

    public RegistroAtendimentoEntity(Integer id, LocalDateTime dataHora, TipoAtendimentoEntity tipoAtendimento, BeneficiarioEntity beneficiario) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
        this.beneficiario = beneficiario;
    }

    public RegistroAtendimentoEntity() {
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

    public TipoAtendimentoEntity getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimentoEntity tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public BeneficiarioEntity getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(BeneficiarioEntity beneficiario) {
        this.beneficiario = beneficiario;
    }
}
