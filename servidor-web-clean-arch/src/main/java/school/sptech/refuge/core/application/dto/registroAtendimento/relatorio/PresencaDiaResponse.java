package school.sptech.refuge.core.application.dto.registroAtendimento.relatorio;

import java.util.List;

public class PresencaDiaResponse {
    private String email;
    private List<PresencaDia> presencas;

    public PresencaDiaResponse(String email, List<PresencaDia> presencas) {
        this.email = email;
        this.presencas = presencas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PresencaDia> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<PresencaDia> presencas) {
        this.presencas = presencas;
    }
}
