package school.sptech.refuge.core.domain.beneficiario.valueobject;

import school.sptech.refuge.core.application.exception.RacaInvalidaException;

public enum RacaEnum {
        BRANCO("Branco(a)"),
        PRETO("Preto(a)"),
        PARDO("Pardo(a)"),
        AMARELA("Amarelo(a)"),
        INDIGENA("Indigena"),
        NAO_DECLARADO("Não declarado");


    private final String descricaoRaca;

    RacaEnum(String descricao) {
        this.descricaoRaca = descricao;
    }

    public String getDescricaoRaca() {
        return descricaoRaca;
    }

    public static RacaEnum fromString(String raca){
        for (RacaEnum racaEnum : RacaEnum.values()){
            if(racaEnum.name().equalsIgnoreCase(raca)
                    || racaEnum.getDescricaoRaca().equalsIgnoreCase(raca)){
                return racaEnum;
            }
        }
        throw new RacaInvalidaException("Tipo de raça inválida: " + raca);
    }


}
