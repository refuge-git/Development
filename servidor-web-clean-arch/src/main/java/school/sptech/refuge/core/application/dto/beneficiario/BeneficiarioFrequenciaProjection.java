package school.sptech.refuge.core.application.dto.beneficiario;

public interface BeneficiarioFrequenciaProjection {
    Integer getId();
    String getNomeRegistro();
    String getNomeSocial();
    Integer getQuantidadePresencas();
    String getFotoPerfil();
    String getImagemUrl();
}