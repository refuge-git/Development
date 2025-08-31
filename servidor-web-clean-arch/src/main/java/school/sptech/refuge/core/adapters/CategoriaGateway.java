package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.categoria.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaGateway {
    Categoria salvar(Categoria categoria);
    List<Categoria> listarTodos();
    Optional<Categoria> buscarPorId(Integer id);
    List<Categoria> buscarPorNome(String nome);
    void deletar(Integer id);
    boolean existePorId(Integer id);
}

