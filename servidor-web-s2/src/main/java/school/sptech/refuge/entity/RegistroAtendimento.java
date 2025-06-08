package school.sptech.refuge.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RegistroAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date data_hora;


    // Relacionamentos da tabela auxiliar
    @ManyToOne
    @JoinColumn(name = "tipo_atendimento_id_tipo_atendimento", nullable = false)
    private TipoAtendimento tipoAtendimento;
    @ManyToOne
    @JoinColumn(name = "beneficiario_id_beneficiario", nullable = false)
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
