package school.sptech.refuge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import school.sptech.refuge.core.domain.beneficiario.valueobject.Beneficiario;

import java.time.LocalDateTime;

@Entity
public class CondicaoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condicao_saude")
    private Integer id;
    @Size(min = 0, max = 100)
    private String diagnostico;
    @Size(min = 0, max = 100)
    private String descricao;
    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    @Size(min = 0, max = 100)
    private String tratamento;
    @Size(min = 0, max = 100)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "fk_beneficiario", referencedColumnName = "id_beneficiario")
    private Beneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "fk_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;

    public CondicaoSaude() {
    }

    public CondicaoSaude(Integer id, String diagnostico, String descricao, LocalDateTime dataRegistro, LocalDateTime dataAtualizacao, String tratamento, String observacoes, Beneficiario beneficiario, Categoria categoria) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.dataAtualizacao = dataAtualizacao;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.beneficiario = beneficiario;
        this.categoria = categoria;
    }
    /** Define a data de registro e atualização antes de persistir o registro */
    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        this.dataRegistro = agora;
        this.dataAtualizacao = agora;
    }

    /** Atualiza a data sempre que o registro for alterado */
    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
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
