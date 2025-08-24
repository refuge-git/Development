package school.sptech.refuge.core.domain.categoria.repository;

import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryInterface {
    Categoria cadastrar(Categoria categoria);
    List<Categoria> listar();
    Optional<Categoria> buscarPorId(Integer id);
    void remover(Integer id);
    List<Categoria> buscarPorNome(String nome);
    Categoria atualizar(Categoria categoria);
}
