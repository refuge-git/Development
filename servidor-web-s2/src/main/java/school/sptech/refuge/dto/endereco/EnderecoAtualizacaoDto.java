package school.sptech.refuge.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoAtualizacaoDto {

    @Schema(description = "CEP do endereço do beneficiário", example = "04241064")
    private String cep;
    @Schema(description = "Rua do endereço do beneficiário", example = "Rua Pereira Augusto")
    private String rua;
    @Schema(description = "Bairro do endereço do beneficiário", example = "Jardim Silveira")
    private String bairro;
    @Schema(description = "Logradouro do endereço do beneficiário", example = "Praça da Árvore")
    private String logradouro;
    @Schema(description = "Número do endereço do beneficiário", example = "314")
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
