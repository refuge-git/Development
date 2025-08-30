package school.sptech.refuge.core.application.usecase.tipogenero;

import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosTipoGeneroUseCase {

    private final TipoGeneroGateway tipoGeneroGateway;

    public ListarTodosTipoGeneroUseCase(TipoGeneroGateway tipoGeneroGateway) {
        this.tipoGeneroGateway = tipoGeneroGateway;
    }

    public List<TipoGeneroListDto> execute() {
        List<TipoGenero> lista = tipoGeneroGateway.listarTodos();

        return lista.stream()
                .map(tipo -> new TipoGeneroListDto(
                        tipo.getId(),
                        tipo.getNome(),
                        tipo.getDescricao()
                ))
                .collect(Collectors.toList());
    }
}
