package school.sptech.refuge.entity;

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

}
