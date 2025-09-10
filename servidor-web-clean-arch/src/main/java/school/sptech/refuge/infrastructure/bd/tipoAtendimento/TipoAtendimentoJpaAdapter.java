package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.util.List;
import java.util.Optional;

public class TipoAtendimentoJpaAdapter implements TipoAtendimentoGateway {
    private final TipoAtendimentoJpaRepository tipoAtendimentoJpaRepository;

    public TipoAtendimentoJpaAdapter(TipoAtendimentoJpaRepository tipoAtendimentoJpaRepository) {
        this.tipoAtendimentoJpaRepository = tipoAtendimentoJpaRepository;
    }

    @Override
    public TipoAtendimento salvar(TipoAtendimento tipoAtendimento) {
        TipoAtendimentoEntity tipoAtendimentoEntity = tipoAtendimentoJpaRepository
                .save(TipoAtendimentoMapper.ofDomain(tipoAtendimento));

        return TipoAtendimentoMapper.ofEntity(tipoAtendimentoEntity);
    }

    @Override
    public List<TipoAtendimento> listar() {
        return List.of();
    }

    @Override
    public Optional<TipoAtendimento> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deletar(TipoAtendimento tipoAtendimento) {

    }

    @Override
    public boolean atualizar(TipoAtendimento tipoAtendimento) {
        return false;
    }

    @Override
    public boolean existePorId(Integer id) {
        return false;
    }
}
