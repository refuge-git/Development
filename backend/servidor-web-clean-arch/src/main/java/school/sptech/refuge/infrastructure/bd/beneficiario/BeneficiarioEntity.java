package school.sptech.refuge.infrastructure.bd.beneficiario;

import jakarta.persistence.*;
import school.sptech.refuge.core.domain.beneficiario.LocalEnum;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;
import school.sptech.refuge.core.domain.beneficiario.StatusEnum;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoEntity;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioEntity;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "beneficiario")
public class BeneficiarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiario")
    private Integer id;
    private String nomeRegistro;
    private String nomeSocial;
    private LocalDate dtNasc;
    private String cpf;
    private Boolean estrangeiro;

    @Enumerated(EnumType.STRING)
    private RacaEnum raca;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
    private String nomeMae;
    private Boolean egressoPrisional;

    @Enumerated(EnumType.STRING)
    private LocalEnum localDorme;
    private String fotoPerfil;
    private String sisa;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private LocalDateTime dataAtivacao;
    private String observacao;


    @ManyToOne
    @JoinColumn(name = "fk_funcionario", referencedColumnName = "id_funcionario")
    private FuncionarioEntity funcionarioEntity;

    @ManyToOne
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id_endereco")
    private EnderecoEntity enderecoEntity;

    @ManyToOne
    @JoinColumn(name = "fk_genero", referencedColumnName = "id_genero")
    private TipoGeneroEntity tipoGeneroEntity;

    @ManyToOne
    @JoinColumn(name = "fk_sexualidade", referencedColumnName = "id_sexualidade")
    private TipoSexualidadeEntity tipoSexualidadeEntity;



    public BeneficiarioEntity(Integer id, String nomeRegistro, String nomeSocial, LocalDate dtNasc, String cpf, Boolean estrangeiro, RacaEnum raca, SexoEnum sexo, String nomeMae, Boolean egressoPrisional, LocalEnum localDorme, String fotoPerfil, String sisa, StatusEnum status, LocalDateTime dataAtivacao, String observacao, FuncionarioEntity funcionarioEntity, EnderecoEntity enderecoEntity, TipoGeneroEntity tipoGeneroEntity, TipoSexualidadeEntity tipoSexualidadeEntity) {
        this.id = id;
        this.nomeRegistro = nomeRegistro;
        this.nomeSocial = nomeSocial;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.estrangeiro = estrangeiro;
        this.raca = raca;
        this.sexo = sexo;
        this.nomeMae = nomeMae;
        this.egressoPrisional = egressoPrisional;
        this.localDorme = localDorme;
        this.fotoPerfil = fotoPerfil;
        this.sisa = sisa;
        this.status = status;
        this.dataAtivacao = dataAtivacao;
        this.observacao = observacao;
        this.funcionarioEntity = funcionarioEntity;
        this.enderecoEntity = enderecoEntity;
        this.tipoGeneroEntity = tipoGeneroEntity;
        this.tipoSexualidadeEntity = tipoSexualidadeEntity;
    }

    public BeneficiarioEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(Boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public RacaEnum getRaca() {
        return raca;
    }

    public void setRaca(RacaEnum raca) {
        this.raca = raca;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Boolean getEgressoPrisional() {
        return egressoPrisional;
    }

    public void setEgressoPrisional(Boolean egressoPrisional) {
        this.egressoPrisional = egressoPrisional;
    }

    public LocalEnum getLocalDorme() {
        return localDorme;
    }

    public void setLocalDorme(LocalEnum localDorme) {
        this.localDorme = localDorme;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(LocalDateTime dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public FuncionarioEntity getFuncionarioEntity() {
        return funcionarioEntity;
    }

    public void setFuncionarioEntity(FuncionarioEntity funcionarioEntity) {
        this.funcionarioEntity = funcionarioEntity;
    }

    public EnderecoEntity getEnderecoEntity() {
        return enderecoEntity;
    }

    public void setEnderecoEntity(EnderecoEntity enderecoEntity) {
        this.enderecoEntity = enderecoEntity;
    }

    public TipoGeneroEntity getTipoGeneroEntity() {
        return tipoGeneroEntity;
    }

    public void setTipoGeneroEntity(TipoGeneroEntity tipoGeneroEntity) {
        this.tipoGeneroEntity = tipoGeneroEntity;
    }

    public TipoSexualidadeEntity getTipoSexualidadeEntity() {
        return tipoSexualidadeEntity;
    }

    public void setTipoSexualidadeEntity(TipoSexualidadeEntity tipoSexualidadeEntity) {
        this.tipoSexualidadeEntity = tipoSexualidadeEntity;
    }
}
