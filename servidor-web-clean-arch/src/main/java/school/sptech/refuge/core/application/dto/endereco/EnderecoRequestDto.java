package school.sptech.refuge.core.application.dto.endereco;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequestDto {

    @NotBlank
    @NotNull
    @Schema(description = "Tipo de Logradouro do endereço do beneficiário", example = "Rua, Avenida, etc")
    private String tipoLogradouro;

    @NotBlank
    @NotNull
    @Schema(description = "Logradouro do endereço do beneficiário", example = "Praça da Árvore")
    private String nomeLogradouro;

    @NotNull
    @Schema(description = "Número do endereço do beneficiário", example = "314")
    private Integer numero;

    @NotBlank
    @NotNull
    @Schema(description = "Complemento do endereço do beneficiário", example = "Apto 1")
    private String complemento;

    @NotBlank
    @NotNull
    private String bairro;

    @NotBlank
    @NotNull
    private String cep;

    @NotBlank
    @NotNull
    private String nomeLocalidade;

    public EnderecoRequestDto(String tipoLogradouro, String nomeLogradouro, Integer numero,
                              String complemento, String bairro, String cep,
                              String nomeLocalidade  ) {
        this.tipoLogradouro = tipoLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.nomeLocalidade = nomeLocalidade;
    }

    public EnderecoRequestDto() {
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

}
