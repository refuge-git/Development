package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.Optional;

public interface CondicaoSaudeGateway {
    CondicaoSaude salvar(CondicaoSaude condicaoSaude);
    List<CondicaoSaude> listarTodos();
    Optional<CondicaoSaude> buscarPorId(Integer id);
    CondicaoSaude atualizar(Integer id, CondicaoSaude condicaoSaude);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Page<CondicaoSaude> listarPaginado(int page, int size);
}

