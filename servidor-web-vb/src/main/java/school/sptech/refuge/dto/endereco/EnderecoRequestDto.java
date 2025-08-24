package school.sptech.refuge.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequestDto {


    @NotBlank(message = "O TIPO DE LOGRADOURO é obrigatório")
    @Schema(description = "Tipo de Logradouro do endereço do beneficiário", example = "Rua, Avenida, etc")
    private String tipoLogradouro;

    @NotBlank(message = "O LOGRADOURO é obrigatório")
    @Schema(description = "Logradouro do endereço do beneficiário", example = "Praça da Árvore")
    private String nomeLogradouro;

    @NotNull(message = "O NÚMERO da casa é obrigatório")
    @Schema(description = "Número do endereço do beneficiário", example = "314")
    private Integer numero;

    @NotBlank(message = "O COMPLEMENTO é obrigatório")
    @Schema(description = "Complemento do endereço do beneficiário", example = "Apto 1")
    private String complemento;

    @NotBlank(message = "O nome do BAIRRO é obrigatório")
    @Schema(description = "Bairro do endereço do beneficiário", example = "Jardim Silveira")
    private String bairro;

    @NotBlank(message = "O CEP é obrigatório")
    @Schema(description = "CEP do endereço do beneficiário", example = "04241064")
    private String cep;

    @NotBlank(message = "O NOME DA LOCALIDADE é obrigatório")
    @Schema(description = "Nome da localidade do endereço do beneficiário", example = "São Paulo")
    private String nomeLocalidade;

    @NotBlank(message = "A SIGLA DA CIDADE é obrigatório")
    @Schema(description = "Sigla da cidade do endereço do beneficiário", example = "SP")
    private String siglaCidade;

    public EnderecoRequestDto(String tipoLogradouro, String nomeLogradouro, Integer numero, String complemento, String bairro, String cep, String nomeLocalidade, String siglaCidade) {
        this.tipoLogradouro = tipoLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.nomeLocalidade = nomeLocalidade;
        this.siglaCidade = siglaCidade;
    }

    public EnderecoRequestDto() {
    }

    public @NotBlank(message = "O TIPO DE LOGRADOURO é obrigatório") String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(@NotBlank(message = "O TIPO DE LOGRADOURO é obrigatório") String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public @NotBlank(message = "O LOGRADOURO é obrigatório") String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(@NotBlank(message = "O LOGRADOURO é obrigatório") String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public @NotNull(message = "O NÚMERO da casa é obrigatório") Integer getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(message = "O NÚMERO da casa é obrigatório") Integer numero) {
        this.numero = numero;
    }

    public @NotBlank(message = "O COMPLEMENTO é obrigatório") String getComplemento() {
        return complemento;
    }

    public void setComplemento(@NotBlank(message = "O COMPLEMENTO é obrigatório") String complemento) {
        this.complemento = complemento;
    }

    public @NotBlank(message = "O nome do BAIRRO é obrigatório") String getBairro() {
        return bairro;
    }

    public void setBairro(@NotBlank(message = "O nome do BAIRRO é obrigatório") String bairro) {
        this.bairro = bairro;
    }

    public @NotBlank(message = "O CEP é obrigatório") String getCep() {
        return cep;
    }

    public void setCep(@NotBlank(message = "O CEP é obrigatório") String cep) {
        this.cep = cep;
    }

    public @NotBlank(message = "O NOME DA LOCALIDADE é obrigatório") String getNomeLocalidade() {
        return nomeLocalidade;
    }

    public void setNomeLocalidade(@NotBlank(message = "O NOME DA LOCALIDADE é obrigatório") String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    public @NotBlank(message = "A SIGLA DA CIDADE é obrigatório") String getSiglaCidade() {
        return siglaCidade;
    }

    public void setSiglaCidade(@NotBlank(message = "A SIGLA DA CIDADE é obrigatório") String siglaCidade) {
        this.siglaCidade = siglaCidade;
    }
}
