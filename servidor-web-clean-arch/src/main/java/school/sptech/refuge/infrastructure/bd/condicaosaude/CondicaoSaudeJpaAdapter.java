package school.sptech.refuge.infrastructure.bd.condicaosaude;

import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CondicaoSaudeJpaAdapter implements CondicaoSaudeGateway {

    private final CondicaoSaudeJpaRepository condicaoSaudeJpaRepository;

    public CondicaoSaudeJpaAdapter(CondicaoSaudeJpaRepository condicaoSaudeJpaRepository) {
        this.condicaoSaudeJpaRepository = condicaoSaudeJpaRepository;
    }

    @Override
    public CondicaoSaude salvar(CondicaoSaude condicaoSaude) {
        CondicaoSaudeEntity condicaoSaudeEntity = condicaoSaudeJpaRepository.save(
                Objects.requireNonNull(CondicaoSaudeMapper.ofDomain(condicaoSaude))
        );
        return CondicaoSaudeMapper.ofEntity(condicaoSaudeEntity);
    }

    @Override
    public List<CondicaoSaude> listarTodos() {
        return condicaoSaudeJpaRepository.findAll()
                .stream()
                .map(CondicaoSaudeMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorId(Integer id) {
        return condicaoSaudeJpaRepository.existsById(id);
    }

    @Override
    public Optional<CondicaoSaude> buscarPorId(Integer id) {
        return condicaoSaudeJpaRepository.findById(id)
                .map(CondicaoSaudeMapper::ofEntity);
    }

    @Override
    public CondicaoSaude atualizar(Integer id, CondicaoSaude condicaoSaude) {
        if (!condicaoSaudeJpaRepository.existsById(id)) {
            throw new CondicaoSaudeNaoEncontradaException("Condição de saúde não encontrada de id: " + id);
        }

        CondicaoSaudeEntity entity = CondicaoSaudeMapper.ofDomain(condicaoSaude);
        assert entity != null;
        entity.setId(id);

        CondicaoSaudeEntity atualizado = condicaoSaudeJpaRepository.save(entity);
        return CondicaoSaudeMapper.ofEntity(atualizado);
    }

    @Override
    public void deletar(Integer id) {
        if (condicaoSaudeJpaRepository.existsById(id)) {
            condicaoSaudeJpaRepository.deleteById(id);
        } else {
            throw new CondicaoSaudeNaoEncontradaException("Condição de saúde não encontrada de id: " + id);
        }
    }
}
