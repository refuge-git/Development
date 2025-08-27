package school.sptech.refuge.core.domain.beneficiario.valueobject;

import school.sptech.refuge.core.application.exception.StatusInvalidoExeption;

public enum StatusEnum {
    ATIVO ("Usuário Ativo"),
    INATIVO ("Usuário Inativo"),
    BANIDO ("Usuário Banido"),
    SUSPENSO("Usuário Suspenso");


    private final String descricaoStatus;

    StatusEnum(String descricaoStatus) {
        this.descricaoStatus = descricaoStatus;
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public static StatusEnum fromString(String status){
        for (StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.name().equalsIgnoreCase(status)){
                return statusEnum;
            }
        }
        throw new StatusInvalidoExeption("Tipo de Situação inválida: "+ status);
    }
}
