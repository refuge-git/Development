package school.sptech.refuge.infrastructure.bd.condicaosaude;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.CondicaoSaudeGateway;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.condicaosaude.CondicaoSaude;
import school.sptech.refuge.core.domain.paginacao.Page;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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

    @Override
    public Page<CondicaoSaude> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        org.springframework.data.domain.Page<CondicaoSaudeEntity> result = condicaoSaudeJpaRepository.findAll(pageable);

        List<CondicaoSaude> condicaoSaudes = result.getContent().stream()
                .map(CondicaoSaudeMapper::ofEntity)
                .toList();

        return new Page<>(condicaoSaudes, result.getTotalElements(), page, size);
    }
}
