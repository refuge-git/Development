package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TipoAtendimentoGateway {
    TipoAtendimento salvar(TipoAtendimento tipoAtendimento);
    List<TipoAtendimento> listar();
    Optional<TipoAtendimento> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean atualizar(TipoAtendimento tipoAtendimento);
    boolean existePorId(Integer id);
    List<Integer> listarIdsRealizadosPorBeneficiarioNaData(Integer beneficiarioId, LocalDate data);
}
