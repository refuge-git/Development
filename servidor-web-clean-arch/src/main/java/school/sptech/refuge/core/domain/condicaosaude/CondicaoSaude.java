package school.sptech.refuge.core.domain.condicaosaude;

import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;

import java.time.LocalDateTime;

public class CondicaoSaude {

    private Integer id;

    private String diagnostico;

    private String descricao;

    private LocalDateTime dataRegistro;

    private LocalDateTime dataAtualizacao;

    private String tratamento;

    private String observacoes;


    private BeneficiarioEntity beneficiarioEntity;


    private CategoriaEntity categoriaEntity;

    public CondicaoSaude() {
    }

    public CondicaoSaude(Integer id, String diagnostico, String descricao, LocalDateTime dataRegistro, LocalDateTime dataAtualizacao, String tratamento, String observacoes, BeneficiarioEntity beneficiarioEntity, CategoriaEntity categoriaEntity) {
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
    /** Define a data de registro e atualização antes de persistir o registro */
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        this.dataRegistro = agora;
        this.dataAtualizacao = agora;
    }

    /** Atualiza a data sempre que o registro for alterado */

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
