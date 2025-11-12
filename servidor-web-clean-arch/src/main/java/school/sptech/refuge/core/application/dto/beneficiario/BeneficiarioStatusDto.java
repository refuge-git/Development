package school.sptech.refuge.core.application.dto.beneficiario;

public class BeneficiarioStatusDto {

    private Integer idBeneficiario;
    private String nomeRegistro;
    private String status;
    private String fotoPerfil;
    private String imagemUrl;

    public BeneficiarioStatusDto(Integer idBeneficiario, String nomeRegistro, String status, String fotoPerfil, String imagemUrl) {
        this.idBeneficiario = idBeneficiario;
        this.nomeRegistro = nomeRegistro;
        this.status = status;
        this.fotoPerfil = fotoPerfil;
        this.imagemUrl = imagemUrl;
    }

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
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
