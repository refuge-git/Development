package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.domain.categoria.repository.CategoriaRepositoryInterface;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;

import java.util.Optional;

public class ListarCategoriaPorIdUseCase {
    private final CategoriaRepositoryInterface categoriaRepository;

    public ListarCategoriaPorIdUseCase(CategoriaRepositoryInterface categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> executar(Integer id) {
        return categoriaRepository.buscarPorId(id);
    }
}
