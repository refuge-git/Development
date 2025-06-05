package school.sptech.refuge.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipoAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_TipoAtendimento;
    private String nome;
    private String descricao;
    private Date dt_criacao;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToMany(mappedBy = "atendimentos") // Atributo referenciado no Beneficiario
    private Set<Beneficiario> beneficiarios = new HashSet<>();
}
