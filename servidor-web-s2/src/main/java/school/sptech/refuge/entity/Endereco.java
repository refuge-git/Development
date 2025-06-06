package school.sptech.refuge.entity;

import jakarta.persistence.*;
import school.sptech.refuge.dto.endereco.EnderecoListDto;

import java.util.List;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer id;
    private String tipoLogradouro;
    private String nomeLogradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String nomeLocalidade;
    private String siglaCidade;

    public Endereco(Integer id, String tipoLogradouro, String nomeLogradouro, Integer numero, String complemento, String bairro, String cep, String nomeLocalidade, String siglaCidade) {
        this.id = id;
        this.tipoLogradouro = tipoLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.nomeLocalidade = nomeLocalidade;
        this.siglaCidade = siglaCidade;
    }

    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSiglaCidade() {
        return siglaCidade;
    }

    public void setSiglaCidade(String siglaCidade) {
        this.siglaCidade = siglaCidade;
    }
}
