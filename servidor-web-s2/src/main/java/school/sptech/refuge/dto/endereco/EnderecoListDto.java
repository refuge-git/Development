package school.sptech.refuge.dto.endereco;

import school.sptech.refuge.entity.Beneficiario;

public class EnderecoListDto {

    private Integer id;
    private String cep;
    private String rua;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private Beneficiario beneficiario;

    public EnderecoListDto(Integer id, String cep, String rua, String bairro, String logradouro, Integer numero) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.beneficiario = beneficiario;
    }

    public EnderecoListDto() {
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
}
