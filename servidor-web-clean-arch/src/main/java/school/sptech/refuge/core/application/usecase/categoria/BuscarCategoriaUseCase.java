package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.domain.categoria.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarCategoriaUseCase{

    private final CategoriaGateway categoriaGateway;

    public BuscarCategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    public CategoriaListDto execute(Integer id) {
        Categoria categoria = categoriaGateway.buscarPorId(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Tipo de gênero não encontrado para o id: " + id));


        return new CategoriaListDto(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
