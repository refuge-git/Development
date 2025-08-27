package school.sptech.refuge.core.repository;

import org.springframework.stereotype.Repository;
import school.sptech.refuge.core.domain.categoria.repository.CategoriaRepositoryInterface;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;
import school.sptech.refuge.infrastructure.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryAdapter implements CategoriaRepositoryInterface {
    private final CategoriaRepository categoriaRepository;

    public CategoriaRepositoryAdapter(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria cadastrar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public List<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findAllByNome(nome);
    }

    @Override
    public void remover(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Categoria atualizar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


}
