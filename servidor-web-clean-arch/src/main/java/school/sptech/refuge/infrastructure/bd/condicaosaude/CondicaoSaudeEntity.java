package school.sptech.refuge.infrastructure.bd.condicaosaude;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "condicao_saude")
public class CondicaoSaudeEntity {
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
    private BeneficiarioEntity beneficiarioEntity;

    @ManyToOne
    @JoinColumn(name = "fk_categoria", referencedColumnName = "id_categoria")
    private CategoriaEntity categoriaEntity;

    public CondicaoSaudeEntity(Integer id, String diagnostico, String descricao, LocalDateTime dataRegistro, LocalDateTime dataAtualizacao, String tratamento, String observacoes, BeneficiarioEntity beneficiarioEntity, CategoriaEntity categoriaEntity) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.dataAtualizacao = dataAtualizacao;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.beneficiarioEntity = beneficiarioEntity;
        this.categoriaEntity = categoriaEntity;
    }

    public CondicaoSaudeEntity() {
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

    public BeneficiarioEntity getBeneficiario() {
        return beneficiarioEntity;
    }

    public void setBeneficiario(BeneficiarioEntity beneficiarioEntity) {
        this.beneficiarioEntity = beneficiarioEntity;
    }

    public CategoriaEntity getCategoria() {
        return categoriaEntity;
    }

    public void setCategoria(CategoriaEntity categoriaEntity) {
        this.categoriaEntity = categoriaEntity;
    }
}
