package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.*;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.repository.*;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TipoGeneroRepository tipoGeneroRepository;
    private final TipoSexualidadeRepository tipoSexualidadeRepository;
    private final CondicaoSaudeRepository condicaoSaudeRepository;
    private final RegistroAtendimentoRepository registroAtendimentoRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, FuncionarioRepository funcionarioRepository, EnderecoRepository enderecoRepository, TipoGeneroRepository tipoGeneroRepository, TipoSexualidadeRepository tipoSexualidadeRepository, CondicaoSaudeRepository condicaoSaudeRepository, RegistroAtendimentoRepository registroAtendimentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.tipoGeneroRepository = tipoGeneroRepository;
        this.tipoSexualidadeRepository = tipoSexualidadeRepository;
        this.condicaoSaudeRepository = condicaoSaudeRepository;
        this.registroAtendimentoRepository = registroAtendimentoRepository;
    }

    public Beneficiario cadastrar(Beneficiario beneficiario) {
        Funcionario funcionario = validarFuncionario(beneficiario.getFuncionario().getId());
        Endereco endereco = validarEndereco(beneficiario.getEndereco().getId());
        TipoGenero tipoGenero = validarTipoGenero(beneficiario.getTipoGenero().getId());
        TipoSexualidade tipoSexualidade = validarTipoSexualidade(beneficiario.getTipoSexualidade().getId());

        beneficiario.setFuncionario(funcionario);
        beneficiario.setEndereco(endereco);
        beneficiario.setTipoGenero(tipoGenero);
        beneficiario.setTipoSexualidade(tipoSexualidade);

        return beneficiarioRepository.save(beneficiario);
    }

    public Funcionario validarFuncionario(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));
    }

    public Endereco validarEndereco(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado"));
    }

    public TipoGenero validarTipoGenero(Integer id) {
        return tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado"));
    }

    public TipoSexualidade validarTipoSexualidade(Integer id) {
        return tipoSexualidadeRepository.findById(id)
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrado"));
    }

    public Beneficiario buscarPorId(Integer id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário de id %d não encontrado".formatted(id)));
    }

    public List<Beneficiario> listar() {

        return beneficiarioRepository.findAll();
    }

    public Beneficiario atualizar(Beneficiario beneficiario) {
        if (beneficiarioRepository.existsById(beneficiario.getId())) {
            beneficiario.setId(beneficiario.getId());
            return beneficiarioRepository.save(beneficiario);
        } else {
            throw new BeneficiarioNaoEncontradaException("Beneficiario de id %d não encontrado".formatted(beneficiario.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (beneficiarioRepository.existsById(id)) {
            // Deleta as condições de saúde relacionadas antes
            registroAtendimentoRepository.deleteAllByBeneficiarioId(id);
            condicaoSaudeRepository.deleteAllByBeneficiarioId(id);
            // Agora deleta o beneficiário
            beneficiarioRepository.deleteById(id);
        } else {
            throw new BeneficiarioNaoEncontradaException("Beneficiário de id %d não encontrado".formatted(id));
        }
    }

    public List<Beneficiario> listarPorSexo(SexoEnum sexo) {
       return beneficiarioRepository.findBySexo(sexo);

    }

    public List<Beneficiario> listarPorRaca(RacaEnum raca) {
        return beneficiarioRepository.findByRaca(raca);
    }

    public List<Beneficiario> listarPorTipoSexualidade(String nomeSexualidade) {
        List<Beneficiario> beneficiarios = beneficiarioRepository.findByNomeTipoSexualidade(nomeSexualidade);

        if (beneficiarios.isEmpty()) {
            throw new BeneficiarioNaoEncontradaException(
                    "Nenhum beneficiário com sexualidade '%s' foi encontrado.".formatted(nomeSexualidade)
            );
        }

        return beneficiarios;
    }

    public List<Beneficiario> listarPorTipoGenero(String nomeGenero){
        List<Beneficiario> beneficiarios = beneficiarioRepository.findByNomeTipoGenero(nomeGenero);

        if (beneficiarios.isEmpty()) {
            throw new BeneficiarioNaoEncontradaException(
                    "Nenhum beneficiário com gênero '%s' foi encontrado.".formatted(nomeGenero)
            );
        }

        return beneficiarios;
    }

    public List<Beneficiario> listarNomeSocial(String nome) {
        return beneficiarioRepository.findByNomeSocialContainingIgnoreCase(nome);

    }

    public List<Beneficiario> listarNomeRegistro(String nome) {
        return beneficiarioRepository.findByNomeRegistroContainingIgnoreCase(nome);
    }

}
