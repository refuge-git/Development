package school.sptech.refuge.core.application.dto.beneficiario;

public class BeneficiarioFrequenciaDto {

    private Integer id;
    private String nomeRegistro;
    private String nomeSocial;
    private Integer totalPresencas;

    public BeneficiarioFrequenciaDto(Integer id, String nomeRegistro, String nomeSocial,
                                     Integer totalPresencas) {
        this.id = id;
        this.nomeRegistro = nomeRegistro;
        this.nomeSocial = nomeSocial;
        this.totalPresencas = totalPresencas;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeRegistro() {
        return nomeRegistro;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public Integer getTotalPresencas() {
        return totalPresencas;
    }

}
