package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

import java.util.List;
import java.util.Optional;

public interface TipoSexualidadeGateway {
    TipoSexualidade salvar(TipoSexualidade tipoSexualidade);
    List<TipoSexualidade> listarTodos();
    boolean existePorId(Integer id);
    Optional<TipoSexualidade> buscarPorId(Integer id);
    TipoSexualidade atualizar(Integer id, TipoSexualidade tipoSexualidade);
    void deletar(Integer id);
}
