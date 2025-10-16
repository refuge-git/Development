package school.sptech.refuge.core.application.dto.registroAtendimento;

public class AtendimentosPorDiaDto {

    private String label; // ex: "Mon", "Tue"
    private Integer quantidadeBanhos;
    private Integer quantidadeRefeicoes;
    private Integer quantidadeOutros;

    public AtendimentosPorDiaDto(String label, Integer quantidadeBanhos, Integer quantidadeRefeicoes, Integer quantidadeOutros) {
        this.label = label;
        this.quantidadeBanhos = quantidadeBanhos;
        this.quantidadeRefeicoes = quantidadeRefeicoes;
        this.quantidadeOutros = quantidadeOutros;
    }

    public String getLabel() {
        return label;
    }

    public Integer getQuantidadeBanhos() {
        return quantidadeBanhos;
    }

    public Integer getQuantidadeRefeicoes() {
        return quantidadeRefeicoes;
    }

    public Integer getQuantidadeOutros() {
        return quantidadeOutros;
    }




}
