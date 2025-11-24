
// school.sptech.refuge.core.application.usecase.tipoAtendimento.BuscarAtividadesRealizadasHojeUseCase
package school.sptech.refuge.core.application.usecase.tipoAtendimento;

import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;

import java.time.LocalDate;
import java.util.List;

public class BuscarAtividadesRealizadasHojeUseCase {

    private final TipoAtendimentoGateway tipoAtendimentoGateway;

    public BuscarAtividadesRealizadasHojeUseCase(TipoAtendimentoGateway tipoAtendimentoGateway) {
        this.tipoAtendimentoGateway = tipoAtendimentoGateway;
    }

    public List<Integer> execute(Integer beneficiarioId, LocalDate data) {
        return tipoAtendimentoGateway.listarIdsRealizadosPorBeneficiarioNaData(beneficiarioId, data);
    }
}
