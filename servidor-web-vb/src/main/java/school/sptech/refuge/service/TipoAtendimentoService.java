package school.sptech.refuge.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.domain.funcionario.valueobject.Funcionario;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.repository.FuncionarioRepository;
import school.sptech.refuge.repository.TipoAtendimentoRepository;

import java.util.List;

@Service
public class TipoAtendimentoService {

    private final TipoAtendimentoRepository tipoAtendimentoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public TipoAtendimentoService(TipoAtendimentoRepository tipoAtendimentoRepository, FuncionarioRepository funcionarioRepository) {
        this.tipoAtendimentoRepository = tipoAtendimentoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario validarFuncionario(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));
    }
    // CREATE
    public TipoAtendimento criar(TipoAtendimento tipoAtendimento ){
        Funcionario funcionario = validarFuncionario(tipoAtendimento.getFuncionario().getId());
        tipoAtendimento.setFuncionario(funcionario);
        return tipoAtendimentoRepository.save(tipoAtendimento);
    }

    // READ
    public List<TipoAtendimento> listarTodos(){
        return tipoAtendimentoRepository.findAll();
    }

    public TipoAtendimento buscarPorId(Integer id){
        return tipoAtendimentoRepository.findById(id)
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento de id %d não encontrado".formatted(id)));
    }

    // UPDATE
    public TipoAtendimento atualizar(TipoAtendimento tipoAtendimento){
        if (tipoAtendimentoRepository.existsById(tipoAtendimento.getId())) {
            tipoAtendimento.setId(tipoAtendimento.getId());
            return tipoAtendimentoRepository.save(tipoAtendimento);
        } else {
            throw new TipoAtendimentoNaoEncotradoException("Tipo de atendimento de id %d não encontrado".formatted(tipoAtendimento.getId()));
        }
    }

    public void deletar(Integer id) {
        if (!tipoAtendimentoRepository.existsById(id)) {
            throw new TipoAtendimentoNaoEncotradoException("Tipo de atendimento de id %d não encontrado".formatted(id));
        }

        try {
            tipoAtendimentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o tipo de atendimento, pois existem registros relacionados a ele.");
        }
    }
}
