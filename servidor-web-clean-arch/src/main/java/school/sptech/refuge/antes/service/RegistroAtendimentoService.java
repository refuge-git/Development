package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.antes.entity.Beneficiario;
import school.sptech.refuge.antes.entity.RegistroAtendimento;
import school.sptech.refuge.antes.entity.TipoAtendimento;
import school.sptech.refuge.antes.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.antes.exception.RegistroAtendimentoNaoEncontradoException;
import school.sptech.refuge.antes.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.antes.repository.BeneficiarioRepository;
import school.sptech.refuge.antes.repository.RegistroAtendimentoRepository;
import school.sptech.refuge.antes.repository.TipoAtendimentoRepository;

import java.util.List;

@Service
public class RegistroAtendimentoService {

    private final RegistroAtendimentoRepository registroAtendimentoRepository;
    private final TipoAtendimentoRepository tipoAtendimentoRepository;
    private final BeneficiarioRepository beneficiarioRepository;

    public RegistroAtendimentoService(RegistroAtendimentoRepository registroAtendimentoRepository, TipoAtendimentoRepository tipoAtendimentoRepository, BeneficiarioRepository beneficiarioRepository) {
        this.registroAtendimentoRepository = registroAtendimentoRepository;
        this.tipoAtendimentoRepository = tipoAtendimentoRepository;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public TipoAtendimento validarTipoAtendimento(Integer id) {
        return tipoAtendimentoRepository.findById(id)
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento não encontrado"));
    }

    public Beneficiario validarBeneficiario(Integer id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário não encontrado"));
    }
    // CREATE
    public RegistroAtendimento criar(RegistroAtendimento registroAtendimento ){
        TipoAtendimento tipoAtendimento = validarTipoAtendimento(registroAtendimento.getTipoAtendimento().getId());
        Beneficiario beneficiario = validarBeneficiario(registroAtendimento.getBeneficiario().getId());
        registroAtendimento.setTipoAtendimento(tipoAtendimento);
        registroAtendimento.setBeneficiario(beneficiario);

        return registroAtendimentoRepository.save(registroAtendimento);
    }

    // READ
    public List<RegistroAtendimento> listarTodos(){
        return registroAtendimentoRepository.findAll();
    }

    public RegistroAtendimento buscarPorId(Integer id){
        return registroAtendimentoRepository.findById(id)
                .orElseThrow(() -> new RegistroAtendimentoNaoEncontradoException("Registro de atendimento de id %d não encontrado".formatted(id)));
    }

    // UPDATE
    public RegistroAtendimento atualizar(RegistroAtendimento registroAtendimento){
        if (registroAtendimentoRepository.existsById(registroAtendimento.getId())) {
            registroAtendimento.setId(registroAtendimento.getId());
            return registroAtendimentoRepository.save(registroAtendimento);
        } else {
            throw new RegistroAtendimentoNaoEncontradoException("Registro de atendimento de id %d não encontrado".formatted(registroAtendimento.getId()));
        }
    }

    public void deletar(Integer id) {
        if (!registroAtendimentoRepository.existsById(id)) {
            throw new RegistroAtendimentoNaoEncontradoException("Registro de atendimento de id %d não encontrado".formatted(id));
        }

        try {
            registroAtendimentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o registro de atendimento, pois existem registros relacionados a ele.");
        }
    }
}
