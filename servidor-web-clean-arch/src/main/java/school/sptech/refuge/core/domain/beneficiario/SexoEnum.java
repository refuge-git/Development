package school.sptech.refuge.core.domain.beneficiario;


import school.sptech.refuge.core.application.exception.SexoInvalidoException;

public enum SexoEnum {
    FEMININO("Femino"),
    MASCULINO("Masculino"),
    NAO_DECLARADO("Não Declarado");

    private final String descricaoSexo;

    SexoEnum(String descricao) {
        this.descricaoSexo = descricao;
    }

    public String getDescricaoSexo() {
        return descricaoSexo;
    }

    public static SexoEnum fromString(String sexo){
        for (SexoEnum sexoEnum : SexoEnum.values()){
            if(sexoEnum.name().equalsIgnoreCase(sexo)){
                return sexoEnum;
            }
        }
        throw new SexoInvalidoException("Tipo de sexo inválido: "+ sexo);
    }
}
