package school.sptech.refuge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import javax.naming.Name;
import java.time.LocalDate;

@Entity
public class CondicaoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condicao_saude")
    private Integer id;
    @Size(min = 0, max = 100)
    private String descricao;
    @Column(name = "data_registro")
    private LocalDate dataRegistro;
    @Size(min = 0, max = 100)
    private String tratamento;
    @Size(min = 0, max = 100)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "fk_beneficiario", referencedColumnName = "id")
    private Beneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "fk_categoria", referencedColumnName = "id")
    private Categoria categoria;

    public CondicaoSaude() {
    }

    public CondicaoSaude(Integer id, String descricao, LocalDate dataRegistro, String tratamento, String observacoes, Beneficiario beneficiario, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.beneficiario = beneficiario;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
