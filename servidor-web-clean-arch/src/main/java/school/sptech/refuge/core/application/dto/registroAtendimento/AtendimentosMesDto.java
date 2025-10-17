package school.sptech.refuge.core.application.dto.registroAtendimento;

public class AtendimentosMesDto {
    private String diaMes;
    private Long quantidadeAtendimentos;

    public AtendimentosMesDto(String diaMes, Long quantidadeAtendimentos) {
        this.diaMes = diaMes;
        this.quantidadeAtendimentos = quantidadeAtendimentos;
    }

    public String getDiaMes() { return diaMes; }
    public Long getQuantidadeAtendimentos() { return quantidadeAtendimentos; }
}
