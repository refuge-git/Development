package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

import java.util.List;
import java.util.Optional;

public interface CondicaoSaudeGateway {
    CondicaoSaude salvar(CondicaoSaude condicaoSaude);
    List<CondicaoSaude> listarTodos();
    Optional<CondicaoSaude> buscarPorId(Integer id);
    List<CondicaoSaude> buscarPorDescricao(String descricao);
    List<CondicaoSaude> buscarPorBeneficiarioId(Integer beneficiarioId);

    CondicaoSaude atualizar(Integer id, CondicaoSaude condicaoSaude);

    void deletar(Integer id);
    boolean existePorId(Integer id);
}

