package school.sptech.refuge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String cep;

    @NotNull
    @NotBlank
    private String rua;

    @NotNull
    @NotBlank
    private String bairro;

    @NotNull
    @NotBlank
    private String logradouro;

    @NotNull
    @NotBlank
    private String numero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getCep() {
        return cep;
    }

    public void setCep(@NotNull @NotBlank String cep) {
        this.cep = cep;
    }

    public @NotNull @NotBlank String getRua() {
        return rua;
    }

    public void setRua(@NotNull @NotBlank String rua) {
        this.rua = rua;
    }

    public @NotNull @NotBlank String getBairro() {
        return bairro;
    }

    public void setBairro(@NotNull @NotBlank String bairro) {
        this.bairro = bairro;
    }

    public @NotNull @NotBlank String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(@NotNull @NotBlank String logradouro) {
        this.logradouro = logradouro;
    }

    public @NotNull @NotBlank String getNumero() {
        return numero;
    }

    public void setNumero(@NotNull @NotBlank String numero) {
        this.numero = numero;
    }
}
