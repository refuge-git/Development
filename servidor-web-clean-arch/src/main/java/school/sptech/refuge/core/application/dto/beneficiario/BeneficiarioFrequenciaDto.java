package school.sptech.refuge.core.application.dto.beneficiario;

public class BeneficiarioFrequenciaDto {

    private Integer id;
    private String nomeRegistro;
    private String nomeSocial;
    private Integer totalPresencas;
    private String fotoPerfil;
    private String imagemUrl;

    public BeneficiarioFrequenciaDto(Integer id, String nomeRegistro, String nomeSocial, Integer totalPresencas, String fotoPerfil, String imagemUrl) {
        this.id = id;
        this.nomeRegistro = nomeRegistro;
        this.nomeSocial = nomeSocial;
        this.totalPresencas = totalPresencas;
        this.fotoPerfil = fotoPerfil;
        this.imagemUrl = imagemUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public Integer getTotalPresencas() {
        return totalPresencas;
    }

    public void setTotalPresencas(Integer totalPresencas) {
        this.totalPresencas = totalPresencas;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
