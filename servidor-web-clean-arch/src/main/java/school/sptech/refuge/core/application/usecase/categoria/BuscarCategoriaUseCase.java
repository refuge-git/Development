package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarCategoriaUseCase{

    private final CategoriaGateway categoriaGateway;

    public BuscarCategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    public List<CategoriaListDto> execute(String nome) {
        return categoriaGateway.buscarPorNome(nome)
                .stream()
                .map(c -> new CategoriaListDto(
                        c.getId(),
                        c.getNome()
                ))
                .collect(Collectors.toList());
    }
}
