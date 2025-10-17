package school.sptech.refuge.core.application.dto.registroAtendimento;

public class AtendimentosDiaDto {

    private String hora;
    private Long quantidadeAtendimento;

    public AtendimentosDiaDto(String hora, Long quantidadeAtendimento) {
        this.hora = hora;
        this.quantidadeAtendimento = quantidadeAtendimento;
    }

    public String gethora() {
        return hora;
    }

    public Long getQuantidadeAtendimento() {
        return quantidadeAtendimento;
    }


}
