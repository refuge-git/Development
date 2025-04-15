package school.sptech.refuge.entity;

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


}
