package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.antes.entity.*;
import school.sptech.refuge.antes.exception.*;
import school.sptech.refuge.antes.repository.*;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.entity.*;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeJpaRepository;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroJpaRepository;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeEntity;
import school.sptech.refuge.repository.*;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final FuncionarioJpaRepository funcionarioJpaRepository;
    private final EnderecoRepository enderecoRepository;
    private final TipoGeneroJpaRepository tipoGeneroJpaRepository;
    private final TipoSexualidadeRepository tipoSexualidadeRepository;
    private final CondicaoSaudeJpaRepository condicaoSaudeJpaRepository;
    private final RegistroAtendimentoRepository registroAtendimentoRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, FuncionarioJpaRepository funcionarioJpaRepository, EnderecoRepository enderecoRepository, TipoGeneroJpaRepository tipoGeneroJpaRepository, TipoSexualidadeRepository tipoSexualidadeRepository, CondicaoSaudeJpaRepository condicaoSaudeJpaRepository, RegistroAtendimentoRepository registroAtendimentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.funcionarioJpaRepository = funcionarioJpaRepository;
        this.enderecoRepository = enderecoRepository;
        this.tipoGeneroJpaRepository = tipoGeneroJpaRepository;
        this.tipoSexualidadeRepository = tipoSexualidadeRepository;
        this.condicaoSaudeJpaRepository = condicaoSaudeJpaRepository;
        this.registroAtendimentoRepository = registroAtendimentoRepository;
    }

    public BeneficiarioEntity cadastrar(BeneficiarioEntity beneficiarioEntity) {
        Funcionario funcionario = validarFuncionario(beneficiarioEntity.getFuncionario().getId());
        Endereco endereco = validarEndereco(beneficiarioEntity.getEndereco().getId());
        TipoGeneroEntity tipoGeneroEntity = validarTipoGenero(beneficiarioEntity.getTipoGenero().getId());
        TipoSexualidadeEntity tipoSexualidadeEntity = validarTipoSexualidade(beneficiarioEntity.getTipoSexualidade().getId());

        beneficiarioEntity.setFuncionario(funcionario);
        beneficiarioEntity.setEndereco(endereco);
        beneficiarioEntity.setTipoGenero(tipoGeneroEntity);
        beneficiarioEntity.setTipoSexualidade(tipoSexualidadeEntity);

        return beneficiarioRepository.save(beneficiarioEntity);
    }

    public Funcionario validarFuncionario(Integer id) {
        return funcionarioJpaRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));
    }

    public Endereco validarEndereco(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado"));
    }

    public TipoGeneroEntity validarTipoGenero(Integer id) {
        return tipoGeneroJpaRepository.findById(id)
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado"));
    }

    public TipoSexualidadeEntity validarTipoSexualidade(Integer id) {
        return tipoSexualidadeRepository.findById(id)
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrado"));
    }

    public BeneficiarioEntity buscarPorId(Integer id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário de id %d não encontrado".formatted(id)));
    }

    public List<BeneficiarioEntity> listar() {

        return beneficiarioRepository.findAll();
    }

    public BeneficiarioEntity atualizar(BeneficiarioEntity beneficiarioEntity) {
        if (beneficiarioRepository.existsById(beneficiarioEntity.getId())) {
            beneficiarioEntity.setId(beneficiarioEntity.getId());
            return beneficiarioRepository.save(beneficiarioEntity);
        } else {
            throw new BeneficiarioNaoEncontradaException("Beneficiario de id %d não encontrado".formatted(beneficiarioEntity.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (!beneficiarioRepository.existsById(id)) {
            throw new BeneficiarioNaoEncontradaException("Beneficiário de id %d não encontrado".formatted(id));
        }

        try {
            beneficiarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o tipo de sexualidade, pois existem registros relacionados a ele.");
        }
    }

    public List<BeneficiarioEntity> listarPorSexo(SexoEnum sexo) {
       return beneficiarioRepository.findBySexo(sexo);

    }

    public List<BeneficiarioEntity> listarPorRaca(RacaEnum raca) {
        return beneficiarioRepository.findByRaca(raca);
    }

    public List<BeneficiarioEntity> listarPorTipoSexualidade(String nomeSexualidade) {
        List<BeneficiarioEntity> beneficiarioEntities = beneficiarioRepository.findByNomeTipoSexualidade(nomeSexualidade);

        if (beneficiarioEntities.isEmpty()) {
            throw new BeneficiarioNaoEncontradaException(
                    "Nenhum beneficiário com sexualidade '%s' foi encontrado.".formatted(nomeSexualidade)
            );
        }

        return beneficiarioEntities;
    }

    public List<BeneficiarioEntity> listarPorTipoGenero(String nomeGenero){
        List<BeneficiarioEntity> beneficiarioEntities = beneficiarioRepository.findByNomeTipoGenero(nomeGenero);

        if (beneficiarioEntities.isEmpty()) {
            throw new BeneficiarioNaoEncontradaException(
                    "Nenhum beneficiário com gênero '%s' foi encontrado.".formatted(nomeGenero)
            );
        }

        return beneficiarioEntities;
    }

    public List<BeneficiarioEntity> listarNomeSocial(String nome) {
        return beneficiarioRepository.findByNomeSocialContainingIgnoreCase(nome);

    }

    public List<BeneficiarioEntity> listarNomeRegistro(String nome) {
        return beneficiarioRepository.findByNomeRegistroContainingIgnoreCase(nome);
    }

}
