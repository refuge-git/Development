package school.sptech.refuge.entity;

import jakarta.persistence.*;
import school.sptech.refuge.dto.endereco.EnderecoListDto;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cep;
    private String rua;
    private String bairro;
    private String logradouro;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "fk_Beneficiario", referencedColumnName = "id")
    private Beneficiario beneficiario;

    public Endereco(Integer id, String cep, String rua, String bairro, String logradouro, Integer numero, Beneficiario beneficiario) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.beneficiario = beneficiario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public EnderecoListDto enderecoListDto(){
        return new EnderecoListDto(
                this.id,
                this.cep,
                this.rua,
                this.bairro,
                this.logradouro,
                this.numero
        );
    }

}
