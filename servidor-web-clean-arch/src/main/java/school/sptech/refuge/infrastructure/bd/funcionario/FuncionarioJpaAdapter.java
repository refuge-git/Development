package school.sptech.refuge.infrastructure.bd.funcionario;

import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
}
