package school.sptech.refuge.core.application.dto.registroAtendimento;

import java.sql.Date;

public class AtendimentosDiaIntervaloDto {

    private Date dia;
    private Long quantidadeAtendimentos;

    public AtendimentosDiaIntervaloDto(Date dia, Long quantidadeAtendimentos) {
        this.dia = dia;
        this.quantidadeAtendimentos = quantidadeAtendimentos;
    }

    public Date getDia() {
        return dia;
    }

    public Long getQuantidadeAtendimentos() {
        return quantidadeAtendimentos;
    }
}

