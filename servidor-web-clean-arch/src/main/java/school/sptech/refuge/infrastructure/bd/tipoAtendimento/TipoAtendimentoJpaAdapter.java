package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        return tipoAtendimentoJpaRepository.findAll()
                .stream().map(TipoAtendimentoMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoAtendimento> buscarPorId(Integer id) {
        return tipoAtendimentoJpaRepository.findById(id)
                .map(TipoAtendimentoMapper::ofEntity);
    }

    @Override
    public void deletar(Integer id) {
    if (tipoAtendimentoJpaRepository.existsById(id)) {
        tipoAtendimentoJpaRepository.deleteById(id);
    }
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
