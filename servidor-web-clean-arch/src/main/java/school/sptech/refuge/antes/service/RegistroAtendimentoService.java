package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.antes.entity.RegistroAtendimento;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.RegistroAtendimentoNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaRepository;
import school.sptech.refuge.antes.repository.RegistroAtendimentoRepository;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoJpaRepository;

import java.util.List;

@Service
public class RegistroAtendimentoService {

    private final RegistroAtendimentoRepository registroAtendimentoRepository;
    private final TipoAtendimentoJpaRepository tipoAtendimentoJpaRepository;
    private final BeneficiarioJpaRepository beneficiarioJpaRepository;

    public RegistroAtendimentoService(RegistroAtendimentoRepository registroAtendimentoRepository, TipoAtendimentoJpaRepository tipoAtendimentoJpaRepository, BeneficiarioJpaRepository beneficiarioJpaRepository) {
        this.registroAtendimentoRepository = registroAtendimentoRepository;
        this.tipoAtendimentoJpaRepository = tipoAtendimentoJpaRepository;
        this.beneficiarioJpaRepository = beneficiarioJpaRepository;
    }

    public TipoAtendimento validarTipoAtendimento(Integer id) {
        return tipoAtendimentoJpaRepository.findById(id)
                .orElseThrow(() -> new TipoAtendimentoNaoEncotradoException("Tipo de atendimento não encontrado"));
    }

    public BeneficiarioEntity validarBeneficiario(Integer id) {
        return beneficiarioJpaRepository.findById(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário não encontrado"));
    }
    // CREATE
    public RegistroAtendimento criar(RegistroAtendimento registroAtendimento ){
        TipoAtendimento tipoAtendimento = validarTipoAtendimento(registroAtendimento.getTipoAtendimento().getId());
        BeneficiarioEntity beneficiarioEntity = validarBeneficiario(registroAtendimento.getBeneficiario().getId());
        registroAtendimento.setTipoAtendimento(tipoAtendimento);
        registroAtendimento.setBeneficiario(beneficiarioEntity);

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
