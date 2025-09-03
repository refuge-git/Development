package school.sptech.refuge.core.domain.beneficiario;

import school.sptech.refuge.antes.entity.Endereco;
import school.sptech.refuge.antes.entity.Funcionario;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Beneficiario {

    private Integer id;
    private String nomeRegistro;
    private String nomeSocial;
    private LocalDate dtNasc;
    private String cpf;
    private Boolean estrangeiro;
    private RacaEnum raca;
    private SexoEnum sexo;
    private String nomeMae;
    private Boolean egressoPrisional;
    private LocalEnum localDorme;
    private String fotoPerfil;
    private String sisa;
    private StatusEnum status;
    private LocalDateTime dataAtivacao;
    private String observacao;

    private Funcionario funcionario;
    private Endereco endereco;
    private TipoGenero tipoGenero;
    private TipoSexualidade tipoSexualidade;

    public Beneficiario(Integer id,
                        String nomeRegistro,
                        String nomeSocial,
                        LocalDate dtNasc,
                        String cpf,
                        Boolean estrangeiro,
                        RacaEnum raca,
                        SexoEnum sexo,
                        String nomeMae,
                        Boolean egressoPrisional,
                        LocalEnum localDorme,
                        String fotoPerfil,
                        String sisa,
                        StatusEnum status,
                        LocalDateTime dataAtivacao,
                        String observacao,
                        Funcionario funcionario,
                        Endereco endereco,
                        TipoGenero tipoGenero,
                        TipoSexualidade tipoSexualidade) {
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
        this.funcionario = funcionario;
        this.endereco = endereco;
        this.tipoGenero = tipoGenero;
        this.tipoSexualidade = tipoSexualidade;
    }

    public Beneficiario() {
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoGenero getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(TipoGenero tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public TipoSexualidade getTipoSexualidade() {
        return tipoSexualidade;
    }

    public void setTipoSexualidade(TipoSexualidade tipoSexualidade) {
        this.tipoSexualidade = tipoSexualidade;
    }
}
