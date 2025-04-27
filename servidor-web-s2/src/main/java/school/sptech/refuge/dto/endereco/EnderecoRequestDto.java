package school.sptech.refuge.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.sptech.refuge.entity.Beneficiario;

public class EnderecoRequestDto {

    @NotBlank (message = "O CEP é obrigatório")
    private String cep;

    @NotBlank(message = "O nome da RUA é obrigatório")
    private String rua;

    @NotBlank(message = "O nome do BAIRRO é obrigatório")
    private String bairro;

    @NotBlank(message = "O LOGRADOURO é obrigatório")
    private String logradouro;

    @NotNull(message = "O NÚMERO da casa é obrigatório")
    private Integer numero;

    @NotBlank(message = "O ID do Beneficiário é obrigatório")
    private Integer beneficiarioId;

    public EnderecoRequestDto(String cep, String rua, String bairro, String logradouro, Integer numero, Integer beneficiarioId) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.beneficiarioId = beneficiarioId;
    }

    public EnderecoRequestDto() {
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

    public Integer getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Integer beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }
}
