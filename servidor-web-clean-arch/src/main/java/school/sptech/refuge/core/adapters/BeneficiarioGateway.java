package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

import java.util.List;
import java.util.Optional;

public interface BeneficiarioGateway {
    Beneficiario salvar(Beneficiario beneficiario);
    List<Beneficiario> listarTodos();
    boolean existePorId(Integer id);
    Optional<Beneficiario> buscarPorId(Integer id);
    Beneficiario atualizar(Integer id, Beneficiario beneficiario);
    void deletar(Integer id);
}
