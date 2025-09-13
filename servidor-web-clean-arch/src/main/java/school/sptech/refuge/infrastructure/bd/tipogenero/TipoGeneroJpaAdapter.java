package school.sptech.refuge.infrastructure.bd.tipogenero;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoGeneroJpaAdapter implements TipoGeneroGateway {

    private final TipoGeneroJpaRepository tipoGeneroJpaRepository;

    public TipoGeneroJpaAdapter(TipoGeneroJpaRepository tipoGeneroJpaRepository) {
        this.tipoGeneroJpaRepository = tipoGeneroJpaRepository;
    }

    @Override
    public TipoGenero salvar(TipoGenero tipoGenero) {
        TipoGeneroEntity tipoGeneroEntity = tipoGeneroJpaRepository.save(Objects.requireNonNull(TipoGeneroMapper.ofDomain(tipoGenero)));
        return TipoGeneroMapper.ofEntity(tipoGeneroEntity);
    }

    @Override
    public List<TipoGenero> listarTodos() {
        return tipoGeneroJpaRepository.findAll()
                .stream()
                .map(TipoGeneroMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorId(Integer id) {
        return tipoGeneroJpaRepository.existsById(id);
    }

    @Override
    public Optional<TipoGenero> buscarPorId(Integer id) {
        return tipoGeneroJpaRepository.findById(id)
                .map(TipoGeneroMapper::ofEntity);
    }

    @Override
    public TipoGenero atualizar(Integer id, TipoGenero tipoGenero) {
        if (!tipoGeneroJpaRepository.existsById(id)) {
            throw new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado de id:" + id);
        }

        TipoGeneroEntity entity = TipoGeneroMapper.ofDomain(tipoGenero);
        assert entity != null;
        entity.setId(id);

        TipoGeneroEntity atualizado = tipoGeneroJpaRepository.save(entity);
        return TipoGeneroMapper.ofEntity(atualizado);
    }

    @Override
    public void deletar(Integer id) {
        if (tipoGeneroJpaRepository.existsById(id)) {
            tipoGeneroJpaRepository.deleteById(id);
        } else {
            throw new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado de id:" + id);
        }
    }
}
