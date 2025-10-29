package school.sptech.refuge.core.application.dto.beneficiario;

public class BeneficiarioStatusDto {

    private String nomeRegistro;
    private String status;
    private String fotoPerfil;
    private String imagemUrl;

    public BeneficiarioStatusDto(String nomeRegistro, String status, String fotoPerfil, String imagemUrl) {
        this.nomeRegistro = nomeRegistro;
        this.status = status;
        this.fotoPerfil = fotoPerfil;
        this.imagemUrl = imagemUrl;
    }

    public String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
