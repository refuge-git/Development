package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.exeption.EntidadeNaoEncontradaException;
import school.sptech.refuge.exeption.FuncionarioNaoEncontradaException;
import school.sptech.refuge.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario cadastrar(Funcionario funcionario) {

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscarPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário de id %d não encontrado".formatted(id)));
    }

    public List<Funcionario> listar() {

        return funcionarioRepository.findAll();
    }

    public Funcionario atualizar(Funcionario funcionario) {
        if (funcionarioRepository.existsById(funcionario.getId())) {
            funcionario.setId(funcionario.getId());
            return funcionarioRepository.save(funcionario);
        } else {
            throw new EntidadeNaoEncontradaException("Funcionário de id %d não encontrado".formatted(funcionario.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Funcionário de id %d não encontrado".formatted(id));
        }
    }


}
