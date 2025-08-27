package school.sptech.refuge.antes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id_categoria")
    private Integer id;
    @Size(min = 0, max = 45)
    private String nome;

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
