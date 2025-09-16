package school.sptech.refuge.core.application.dto.beneficiario;

public class BeneficiarioStatusDto {

    private String nomeRegistro;
    private String status;

    public BeneficiarioStatusDto(String nomeRegistro, String status) {
        this.nomeRegistro = nomeRegistro;
        this.status = status;
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
}
