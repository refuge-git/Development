package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

import java.util.List;
import java.util.Optional;

public interface TipoGeneroGateway {
    TipoGenero salvar(TipoGenero tipoGenero);
    List<TipoGenero> listarTodos();
    boolean existePorId(Integer id);
    Optional<TipoGenero> buscarPorId(Integer id);
    TipoGenero atualizar(Integer id, TipoGenero tipoGenero);
    void deletar(Integer id);
}
