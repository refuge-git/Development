package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoAtualizarDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoListDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.exeption.AtendimentoNaoEncontradoException;
import school.sptech.refuge.exeption.EnderecoNaoEncontradoException;
import school.sptech.refuge.exeption.EntidadeNaoEncontradaException;
import school.sptech.refuge.repository.EnderecoRepository;
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

    public TipoAtendimento cadastrar(TipoAtendimento tipoAtendimento) {
        return tipoAtendimentoRepository.save(tipoAtendimento);
    }

    public List<TipoAtendimento> listar() {

        return tipoAtendimentoRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Funcionário de id %d não encontrado".formatted(idFuncionario)
                ));
    }

    public TipoAtendimento buscarTipoPorId(Integer id) {
        return tipoAtendimentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de Atendimento de id %d não encontrado".formatted(id)));
    }


    public TipoAtendimento atualizar(TipoAtendimento tipoAtendimento) {
        if (tipoAtendimentoRepository.existsById(tipoAtendimento.getId())) {
            tipoAtendimento.setId(tipoAtendimento.getId());
            return tipoAtendimentoRepository.save(tipoAtendimento);
        } else {
            throw new EntidadeNaoEncontradaException("Tipo de Atendimento de id %d não encontrado".formatted(tipoAtendimento.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (tipoAtendimentoRepository.existsById(id)) {
            tipoAtendimentoRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Tipo de atendimento de id %d não encontrado".formatted(id));
        }
    }
}
