package school.sptech.refuge.entity;

import school.sptech.refuge.exception.RacaInvalidaException;

public enum RacaEnum {
        BRANCO("Branco(a)"),
        PRETO("Preto(a)"),
        PARDO("Pardo(a)"),
        AMARELA("Amarelo(a)"),
        INDIGENA("Indigena");


    private final String descricaoRaca;

    RacaEnum(String descricao) {
        this.descricaoRaca = descricao;
    }

    public String getDescricaoRaca() {
        return descricaoRaca;
    }

    public static RacaEnum fromString(String raca){
        for (RacaEnum racaEnum : RacaEnum.values()){
            if(racaEnum.name().equalsIgnoreCase(raca)){
                return racaEnum;
            }
        }
        throw new RacaInvalidaException("Tipo de raça inválida: "+ raca);
    }


}
