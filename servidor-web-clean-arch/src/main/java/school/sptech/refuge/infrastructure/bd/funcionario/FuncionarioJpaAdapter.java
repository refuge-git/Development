package school.sptech.refuge.infrastructure.bd.funcionario;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.paginacao.Page;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioJpaAdapter implements FuncionarioGateway {
    private final FuncionarioJpaRepository funcionarioJpaRepository;

    public FuncionarioJpaAdapter(FuncionarioJpaRepository funcionarioJpaRepository) {
        this.funcionarioJpaRepository = funcionarioJpaRepository;
    }

    @Override
    public Funcionario salvar(Funcionario funcionario) {
        FuncionarioEntity funcionarioEntity = funcionarioJpaRepository
                .save(FuncionarioMapper.ofDomain(funcionario));

        return FuncionarioMapper.ofEntity(funcionarioEntity);
    }

    @Override
    public List<Funcionario> listarTodos() {
        return funcionarioJpaRepository.findAll()
                .stream()
                .map(FuncionarioMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Funcionario> buscarPorId(Integer id) {
        return funcionarioJpaRepository.findById(id)
                .map(FuncionarioMapper::ofEntity);
    }

    @Override
    public void deletar(Integer id) {
    if (funcionarioJpaRepository.existsById(id)) {
        funcionarioJpaRepository.deleteById(id);
    }
    }

    @Override
    public boolean existePorId(Integer id) {
        return funcionarioJpaRepository.existsById(id);
    }

    @Override
    public Optional<Funcionario> buscarPorEmail(String email) {
        return funcionarioJpaRepository.findByEmail(email)
                .map(FuncionarioMapper::ofEntity);
    }

    @Override
    public Page<Funcionario> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        org.springframework.data.domain.Page<FuncionarioEntity> result = funcionarioJpaRepository.findAll(pageable);

        List<Funcionario> funcionarios = result.getContent().stream()
                .map(FuncionarioMapper::ofEntity)
                .toList();

        return new Page<>(funcionarios, result.getTotalElements(), page, size);
    }

}
