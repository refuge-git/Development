package school.sptech.refuge.core.application.dto.registroAtendimento;

public class AtendimentosPorMesDto {
    private String mes;
    private Long total;

    public AtendimentosPorMesDto(String mes, Long total) {
        this.mes = mes;
        this.total = total;
    }

    public String getMes() { return mes; }
    public Long getTotal() { return total; }
}
