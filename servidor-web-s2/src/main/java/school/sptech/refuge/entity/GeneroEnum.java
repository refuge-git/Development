package school.sptech.refuge.entity;

import school.sptech.refuge.exeption.GeneroInvalidoExeption;
import school.sptech.refuge.exeption.StatusInvalidoExeption;

public enum GeneroEnum {
    MULHER_CIS("Mulher Cisgênero"),
    HOMEM_CIS("Homem Cisgênero"),
    MULHER_TRANS("Mulher Transgênero"),
    HOMEM_TRANS("Homem Transgênero"),
    NÃO_INFORMADO("Não informado"),
    OUTRO("Outro");

    private final String descricaoGenero;

    GeneroEnum(String descricao) {
        this.descricaoGenero = descricao;
    }

    public String getDescricaoGenero() {
        return descricaoGenero;
    }

    public static GeneroEnum fromString(String genero){
        for (GeneroEnum generoEnum : GeneroEnum.values()){
            if(generoEnum.name().equalsIgnoreCase(genero)){
                return generoEnum;
            }
        }
        throw new GeneroInvalidoExeption("Tipo de genero inválido: "+ genero);
    }

}
