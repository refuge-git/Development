package school.sptech.refuge.infrastructure.bd.categoria;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id_categoria")
    private Integer id;
    @Size(min = 0, max = 45)
    private String nome;

    public CategoriaEntity(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaEntity() {
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
