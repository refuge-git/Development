package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioFrequenciaProjection;
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
    void linkEndereco(Integer idBeneficiario, Integer id);
    Page<BeneficiarioFrequenciaProjection> listarPaginadoPorFrequencia(int page, int size, int diaSemana, String search);
    Page<Beneficiario> listarPaginadoPorStatus(int page, int size, String status, String search);
    List<Beneficiario> buscarPorSexualidade(String sexualidade);
    List<Beneficiario> buscarPorNome(String nome);


}
