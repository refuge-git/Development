package school.sptech.refuge.dto.endereco;

public class EnderecoAtualizacaoDto {

    private String cep;
    private String rua;
    private String bairro;
    private String logradouro;
    private Integer numero;

    public EnderecoAtualizacaoDto(String cep, String rua, String bairro, String logradouro, Integer numero) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public EnderecoAtualizacaoDto() {
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
}
