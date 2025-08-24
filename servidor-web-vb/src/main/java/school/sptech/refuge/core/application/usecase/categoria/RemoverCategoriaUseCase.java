package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.domain.categoria.repository.CategoriaRepositoryInterface;

public class RemoverCategoriaUseCase {
    private final CategoriaRepositoryInterface categoriaRepository;

    public RemoverCategoriaUseCase(CategoriaRepositoryInterface categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void executar(Integer id) {
        categoriaRepository.remover(id);
    }
}
