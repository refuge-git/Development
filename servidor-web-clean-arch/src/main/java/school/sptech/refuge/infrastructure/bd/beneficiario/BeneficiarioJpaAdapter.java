package school.sptech.refuge.infrastructure.bd.beneficiario;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioFrequenciaProjection;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.paginacao.Page;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoEntity;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoJpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeneficiarioJpaAdapter implements BeneficiarioGateway {

    private final BeneficiarioJpaRepository beneficiarioJpaRepository;
    private final EnderecoJpaRepository enderecoJpaRepository;

    public BeneficiarioJpaAdapter(BeneficiarioJpaRepository beneficiarioJpaRepository, EnderecoJpaRepository enderecoJpaRepository) {
        this.beneficiarioJpaRepository = beneficiarioJpaRepository;
        this.enderecoJpaRepository = enderecoJpaRepository;
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
    public List<Beneficiario> buscarPorNomeRegistroOuNomeSocial(String nomeR) {
        return beneficiarioJpaRepository.findByNomeRegistroContainingIgnoreCase(nomeR).stream().map(BeneficiarioMapper::ofEntity).collect(Collectors.toList());
    }

    @Override
    public List<Beneficiario> buscarPorPresencaNoDiaAtual(int diaSemana) {
        return beneficiarioJpaRepository.findBeneficiariosComMaisPresencasPorDiaSemana(diaSemana).stream().map(BeneficiarioMapper::ofEntity).collect(Collectors.toList());
    }



    @Override
    public Page<Beneficiario> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        org.springframework.data.domain.Page<BeneficiarioEntity> result = beneficiarioJpaRepository.findAll(pageable);

        List<Beneficiario> beneficiarios = result.getContent().stream()
                .map(BeneficiarioMapper::ofEntity)
                .toList();

        return new Page<>(beneficiarios, result.getTotalElements(), page, size);
    }

    @Override
    public Page<BeneficiarioFrequenciaProjection> listarPaginadoPorFrequencia(int page, int size, int diaSemana, String search) {
        Pageable pageable = PageRequest.of(page - 1, size);

        org.springframework.data.domain.Page<BeneficiarioFrequenciaProjection> result =
                beneficiarioJpaRepository.findBeneficiariosComMaisPresencasPorDiaSemanaPaginado(diaSemana, search, pageable);

        return new Page<>(
                result.getContent(),
                result.getTotalElements(),
                page,
                size
        );
    }

    @Override
    public void linkEndereco(Integer idBeneficiario, Integer idEndereco) {
        BeneficiarioEntity beneficiario = beneficiarioJpaRepository.findById(idBeneficiario)
                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));

        EnderecoEntity endereco = enderecoJpaRepository.findById(idEndereco)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        beneficiario.setEnderecoEntity(endereco);
        beneficiarioJpaRepository.save(beneficiario);
    }

    @Override
    public Page<Beneficiario> listarPaginadoPorStatus(int page, int size, String status, String search) {
 Pageable pageable = PageRequest.of(Math.max(0, page - 1), Math.max(1, size));
            org.springframework.data.domain.Page<BeneficiarioEntity> result = beneficiarioJpaRepository.findByStatusByNome(status, search, pageable);

            List<Beneficiario> beneficiarios = result.getContent().stream()
                    .map(BeneficiarioMapper::ofEntity)
                    .collect(Collectors.toList());

            return new Page<>(beneficiarios, result.getTotalElements(), page, size);
        }
    }

