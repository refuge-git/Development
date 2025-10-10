package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaRequestDto;
import school.sptech.refuge.core.domain.categoria.Categoria;

public class CriarCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public CriarCategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    public CategoriaListDto execute(CategoriaRequestDto requestDto) {
        Categoria categoria = new Categoria();
        categoria.setNome(requestDto.getNome());

        Categoria criada = categoriaGateway.salvar(categoria);

        return new CategoriaListDto(
                criada.getId(),
                criada.getNome()
        );
    }
}
