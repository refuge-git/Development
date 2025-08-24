package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.domain.categoria.repository.CategoriaRepositoryInterface;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;

public class AtualizarCategoriaUseCase {
    private final CategoriaRepositoryInterface categoriaRepository;

    public AtualizarCategoriaUseCase(CategoriaRepositoryInterface categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria executar(Categoria categoria) {
        return categoriaRepository.atualizar(categoria);
    }
}
