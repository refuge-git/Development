package school.sptech.refuge.core.application.usecase.tiposexualidade;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosTipoSexualidadeUseCase {

    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public ListarTodosTipoSexualidadeUseCase(TipoSexualidadeGateway tipoSexualidadeGateway) {
        this.tipoSexualidadeGateway = tipoSexualidadeGateway;
    }

    public List<TipoSexualidadeListDto> execute() {
        List<TipoSexualidade> lista = tipoSexualidadeGateway.listarTodos();

        return lista.stream()
                .map(tipo -> new TipoSexualidadeListDto(
                        tipo.getId(),
                        tipo.getNome(),
                        tipo.getDescricao()
                ))
                .collect(Collectors.toList());
    }
}
