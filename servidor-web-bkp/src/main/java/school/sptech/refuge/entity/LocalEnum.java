package school.sptech.refuge.entity;


import school.sptech.refuge.exception.LocalInvalidoException;

public enum LocalEnum {
    CASA("Casa"),
    RUA("Rua"),
    CENTRO_ACOLHIDA("Centro de Acolhida"),
    PENSAO("Pensão"),
    PASSAGEM_PELA_CIDADE("De passagem pela cidade");


    private final String descricaoLocal;

    LocalEnum(String descricao) {
        this.descricaoLocal = descricao;
    }

    public String getDescricaoLocal() {
        return descricaoLocal;
    }

    public static LocalEnum fromString(String local){
        for (LocalEnum localEnum : LocalEnum.values()){
            if(localEnum.name().equalsIgnoreCase(local)){
                return localEnum;
            }
        }
        throw new LocalInvalidoException("Tipo de local para dormir inválido: "+ local);
    }
}
