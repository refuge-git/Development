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
    private TipoAtendimento tipoAtendimento;
    @ManyToOne
    private Beneficiario beneficiario;
}
