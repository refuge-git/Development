package school.sptech.refuge.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.sptech.refuge.entity.Beneficiario;

public class EnderecoRequestDto {

    @NotBlank (message = "O CEP é obrigatório")
    @Schema(description = "CEP do endereço do beneficiário", example = "04241064")
    private String cep;

    @NotBlank(message = "O nome da RUA é obrigatório")
    @Schema(description = "Rua do endereço do beneficiário", example = "Rua Pereira Augusto")
    private String rua;

    @NotBlank(message = "O nome do BAIRRO é obrigatório")
    @Schema(description = "Bairro do endereço do beneficiário", example = "Jardim Silveira")
    private String bairro;

    @NotBlank(message = "O LOGRADOURO é obrigatório")
    @Schema(description = "Logradouro do endereço do beneficiário", example = "Praça da Árvore")
    private String logradouro;

    @NotNull(message = "O NÚMERO da casa é obrigatório")
    @Schema(description = "Número do endereço do beneficiário", example = "314")
    private Integer numero;

    @NotNull(message = "O ID do Beneficiário é obrigatório")
    @Schema(description = "FK associada ao beneficiário", example = "7")
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
