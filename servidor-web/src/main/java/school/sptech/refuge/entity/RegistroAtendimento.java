package school.sptech.refuge.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RegistroAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "fk_beneficiario", referencedColumnName = "id")
    private Beneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_atendimento", referencedColumnName = "id")
    private TipoAtendimento tipoAtendimento;

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
        this.dataRegistro = dataRegistro;
    }
}
