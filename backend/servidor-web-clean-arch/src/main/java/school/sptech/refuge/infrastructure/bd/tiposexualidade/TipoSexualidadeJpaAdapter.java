package school.sptech.refuge.infrastructure.bd.tiposexualidade;



import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoSexualidadeJpaAdapter implements TipoSexualidadeGateway {

    private final TipoSexualidadeJpaRepository tipoSexualidadeJpaRepository;

    public TipoSexualidadeJpaAdapter(TipoSexualidadeJpaRepository tipoSexualidadeJpaRepository) {
        this.tipoSexualidadeJpaRepository = tipoSexualidadeJpaRepository;
    }

    @Override
    public TipoSexualidade salvar(TipoSexualidade tipoSexualidade) {
        TipoSexualidadeEntity tipoSexualidadeEntity = tipoSexualidadeJpaRepository.save(TipoSexualidadeMapper.ofDomain(tipoSexualidade));
        return TipoSexualidadeMapper.ofEntity(tipoSexualidadeEntity);
    }

    @Override
    public List<TipoSexualidade> listarTodos() {
        return tipoSexualidadeJpaRepository.findAll()
                .stream()
                .map(TipoSexualidadeMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorId(Integer id) {
        return tipoSexualidadeJpaRepository.existsById(id);
    }

    @Override
    public Optional<TipoSexualidade> buscarPorId(Integer id) {
        return tipoSexualidadeJpaRepository.findById(id)
                .map(TipoSexualidadeMapper::ofEntity);
    }

    @Override
    public TipoSexualidade atualizar(Integer id, TipoSexualidade tipoSexualidade) {
        if (!tipoSexualidadeJpaRepository.existsById(id)) {
            throw new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade de id:" + id);
        }

        TipoSexualidadeEntity entity = TipoSexualidadeMapper.ofDomain(tipoSexualidade);
        entity.setId(id);

        TipoSexualidadeEntity atualizado = tipoSexualidadeJpaRepository.save(entity);
        return TipoSexualidadeMapper.ofEntity(atualizado);
    }

    @Override
    public void deletar(Integer id) {
        if (tipoSexualidadeJpaRepository.existsById(id)) {
            tipoSexualidadeJpaRepository.deleteById(id);
        }
    }
}
