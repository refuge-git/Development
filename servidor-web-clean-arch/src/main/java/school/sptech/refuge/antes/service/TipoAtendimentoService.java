package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoJpaRepository;

import java.util.List;

@Service
public class TipoAtendimentoService {

    private final TipoAtendimentoJpaRepository tipoAtendimentoJpaRepository;
    private final FuncionarioJpaRepository funcionarioJpaRepository;

    public TipoAtendimentoService(TipoAtendimentoJpaRepository tipoAtendimentoJpaRepository, FuncionarioJpaRepository funcionarioJpaRepository) {
        this.tipoAtendimentoJpaRepository = tipoAtendimentoJpaRepository;
        this.funcionarioJpaRepository = funcionarioJpaRepository;
    }

    public Funcionario validarFuncionario(Integer id) {
        return funcionarioJpaRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));
    }
    // CREATE
    public TipoAtendimento criar(TipoAtendimento tipoAtendimento ){
        Funcionario funcionario = validarFuncionario(tipoAtendimento.getFuncionario().getId());
        tipoAtendimento.setFuncionario(funcionario);
        return tipoAtendimentoJpaRepository.save(tipoAtendimento);
    }

    // READ
    public List<TipoAtendimento> listarTodos(){
        return tipoAtendimentoJpaRepository.findAll();
    }

    public TipoAtendimento buscarPorId(Integer id){
        return tipoAtendimentoJpaRepository.findById(id)
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento de id %d não encontrado".formatted(id)));
    }

    // UPDATE
    public TipoAtendimento atualizar(TipoAtendimento tipoAtendimento){
        if (tipoAtendimentoJpaRepository.existsById(tipoAtendimento.getId())) {
            tipoAtendimento.setId(tipoAtendimento.getId());
            return tipoAtendimentoJpaRepository.save(tipoAtendimento);
        } else {
            throw new TipoAtendimentoNaoEncotradoException("Tipo de atendimento de id %d não encontrado".formatted(tipoAtendimento.getId()));
        }
    }

    public void deletar(Integer id) {
        if (!tipoAtendimentoJpaRepository.existsById(id)) {
            throw new TipoAtendimentoNaoEncotradoException("Tipo de atendimento de id %d não encontrado".formatted(id));
        }

        try {
            tipoAtendimentoJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o tipo de atendimento, pois existem registros relacionados a ele.");
        }
    }
}
