package school.sptech.refuge.core.application.dto.registroAtendimento;

public class AtendimentosSemanaDto {

    private String diaSemana;
    private Long quantidadeAtendimentos;

    public AtendimentosSemanaDto(String diaSemana, Long quantidadeAtendimentos) {
        this.diaSemana = diaSemana;
        this.quantidadeAtendimentos = quantidadeAtendimentos;
    }

    public String getdiaSemana() {
        return diaSemana;
    }

    public Long getQuantidadeAtendimentos() {
        return quantidadeAtendimentos;
    }
}
