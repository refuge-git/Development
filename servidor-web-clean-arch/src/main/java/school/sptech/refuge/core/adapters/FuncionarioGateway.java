package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioGateway {
    Funcionario salvar(Funcionario funcionario);
    List<Funcionario> listarTodos();
    Optional<Funcionario> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Optional<Funcionario> buscarPorEmail(String email);


}
