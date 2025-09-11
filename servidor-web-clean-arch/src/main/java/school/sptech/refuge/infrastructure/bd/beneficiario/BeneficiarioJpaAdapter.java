package school.sptech.refuge.infrastructure.bd.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BeneficiarioJpaAdapter implements BeneficiarioGateway {

    private final BeneficiarioJpaRepository beneficiarioJpaRepository;

    public BeneficiarioJpaAdapter(BeneficiarioJpaRepository beneficiarioJpaRepository) {
        this.beneficiarioJpaRepository = beneficiarioJpaRepository;
    }

    @Override
    public Beneficiario salvar(Beneficiario beneficiario) {
        BeneficiarioEntity beneficiarioEntity = beneficiarioJpaRepository.save(Objects.requireNonNull(BeneficiarioMapper.ofDomain(beneficiario)));
        return BeneficiarioMapper.ofEntity(beneficiarioEntity);
    }

    @Override
    public List<Beneficiario> listarTodos() {
        return beneficiarioJpaRepository.findAll().stream().map(BeneficiarioMapper::ofEntity).collect(Collectors.toList());
    }

    @Override
    public boolean existePorId(Integer id) {
        return beneficiarioJpaRepository.existsById(id);
    }

    @Override
    public Optional<Beneficiario> buscarPorId(Integer id) {
        return beneficiarioJpaRepository.findById(id).map(BeneficiarioMapper::ofEntity);
    }

    @Override
    public Beneficiario atualizar(Integer id, Beneficiario beneficiario) {
        if (!beneficiarioJpaRepository.existsById(id)) {;
            throw new BeneficiarioNaoEncontradaException("Bendeficiário não encontrado de id:" + id);
        }

        BeneficiarioEntity entity = BeneficiarioMapper.ofDomain(beneficiario);
        assert entity != null;
        entity.setId(id);

        BeneficiarioEntity atualizado = beneficiarioJpaRepository.save(entity);
        return BeneficiarioMapper.ofEntity(atualizado);
    }

    @Override
    public void deletar(Integer id) {
        if (beneficiarioJpaRepository.existsById(id)) {
            beneficiarioJpaRepository.deleteById(id);
        } else {
            throw new BeneficiarioNaoEncontradaException("Bendeficiário não encontrado de id:" + id);
        }
    }

    @Override
    public List<Beneficiario> buscarPorRaca(String raca) {
        return beneficiarioJpaRepository.findByRaca(raca).stream().map(BeneficiarioMapper::ofEntity).collect(Collectors.toList());
    }

    @Override
    public List<Beneficiario> buscarPorStatus(String status) {
        return beneficiarioJpaRepository.findByStatus(status).stream().map(BeneficiarioMapper::ofEntity).collect(Collectors.toList());
    }

    @Override
    public List<Beneficiario> buscarPorNomeRegistroOuNomeSocial(String nome) {
        return beneficiarioJpaRepository.findByNomeRegistroOrNomeSocialContainingIgnoreCase(nome).stream().map(BeneficiarioMapper::ofEntity).collect(Collectors.toList());
    }
}
