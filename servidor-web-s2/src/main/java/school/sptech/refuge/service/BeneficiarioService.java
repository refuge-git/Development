package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.*;
import school.sptech.refuge.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.repository.*;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TipoGeneroRepository tipoGeneroRepository;
    private final TipoSexualidadeRepository tipoSexualidadeRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, FuncionarioRepository funcionarioRepository, EnderecoRepository enderecoRepository, TipoGeneroRepository tipoGeneroRepository, TipoSexualidadeRepository tipoSexualidadeRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.tipoGeneroRepository = tipoGeneroRepository;
        this.tipoSexualidadeRepository = tipoSexualidadeRepository;
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
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public Endereco validarEndereco(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public TipoGenero validarTipoGenero(Integer id) {
        return tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de gênero não encontrado"));
    }

    public TipoSexualidade validarTipoSexualidade(Integer id) {
        return tipoSexualidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de sexualidade não encontrado"));
    }

    public Beneficiario buscarPorId(Integer id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário de id %d não encontrado".formatted(id)));
    }

    public Beneficiario buscarRelacionamento(Integer id) {
        return beneficiarioRepository.buscarComRelacionamentos(id)
                .orElseThrow(() -> new BeneficiarioNaoEncontradaException("Beneficiário não encontrado"));
    }

    public List<Beneficiario> listar() {

        return beneficiarioRepository.findAll();
    }

    public Beneficiario atualizar(Beneficiario beneficiario) {
        if (beneficiarioRepository.existsById(beneficiario.getId())) {
            beneficiario.setId(beneficiario.getId());
            return beneficiarioRepository.save(beneficiario);
        } else {
            throw new EntidadeNaoEncontradaException("Beneficiario de id %d não encontrado".formatted(beneficiario.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (beneficiarioRepository.existsById(id)) {
            beneficiarioRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Funcionário de id %d não encontrado".formatted(id));
        }
    }

    /*public List<Beneficiario> listarPorGenero(String genero) {
       return beneficiarioRepository.findByTipoGeneroLike(genero);

    }*/

    /*public List<Beneficiario> listarPorRaca(String raca) {
        return beneficiarioRepository.findByRacaContainingIgnoreCase(raca);

    }*/

    public List<Beneficiario> listarNomeSocial(String nome) {
        return beneficiarioRepository.findByNomeSocialContainingIgnoreCase(nome);

    }

    public List<Beneficiario> listarNomeRegistro(String nome) {
        return beneficiarioRepository.findByNomeRegistroContainingIgnoreCase(nome);

    }

}
