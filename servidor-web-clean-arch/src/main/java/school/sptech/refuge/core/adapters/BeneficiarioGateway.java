package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.paginacao.Page;

import java.util.List;
import java.util.Optional;

public interface BeneficiarioGateway {
    Beneficiario salvar(Beneficiario beneficiario);
    List<Beneficiario> listarTodos();
    boolean existePorId(Integer id);
    Optional<Beneficiario> buscarPorId(Integer id);
    List<Beneficiario> buscarPorRaca(String raca);
    List<Beneficiario> buscarPorStatus(String status);
    List<Beneficiario> buscarPorNomeRegistroOuNomeSocial(String nome);
    Beneficiario atualizar(Integer id, Beneficiario beneficiario);
    void deletar(Integer id);
    List<Beneficiario> buscarPorPresencaNoDiaAtual(int diaSemana);
    Page<Beneficiario> listarPaginado(int page, int size);
}
