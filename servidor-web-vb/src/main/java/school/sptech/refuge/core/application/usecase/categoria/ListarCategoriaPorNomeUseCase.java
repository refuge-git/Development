package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.core.domain.categoria.repository.CategoriaRepositoryInterface;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;

import java.util.List;

public class ListarCategoriaPorNomeUseCase {
    private final CategoriaRepositoryInterface categoriaRepository;

    public ListarCategoriaPorNomeUseCase(CategoriaRepositoryInterface categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> executar(String nome) {
        return categoriaRepository.buscarPorNome(nome);
    }
}
