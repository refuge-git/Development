package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaRequestDto;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.domain.categoria.Categoria;

public class AtualizarCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public AtualizarCategoriaUseCase(CategoriaGateway categoriaGateway){
        this.categoriaGateway = categoriaGateway;
    }

    public CategoriaListDto execute(Integer id, CategoriaAtualizacaoDto request){
        Categoria existente = categoriaGateway.buscarPorId(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Tipo de Categoria não encontrado para o id: " + id));
        existente.setNome(request.getNome());

        Categoria atualizado = categoriaGateway.atualizar(id, existente);

        return new CategoriaListDto(
                atualizado.getId(),
                atualizado.getNome()
        );
    }

}
