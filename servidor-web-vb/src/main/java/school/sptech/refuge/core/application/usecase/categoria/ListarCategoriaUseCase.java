package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.domain.categoria.repository.CategoriaRepositoryInterface;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;

import java.util.List;

public class ListarCategoriaUseCase {
    private final CategoriaRepositoryInterface categoriaRepository;

    public ListarCategoriaUseCase(CategoriaRepositoryInterface categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> executar() {
        return categoriaRepository.listar();
    }
}
