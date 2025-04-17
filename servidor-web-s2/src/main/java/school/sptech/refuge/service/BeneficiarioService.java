package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.exeption.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.exeption.EntidadeNaoEncontradaException;
import school.sptech.refuge.exeption.FuncionarioNaoEncontradaException;
import school.sptech.refuge.repository.BeneficiarioRepository;
import school.sptech.refuge.repository.FuncionarioRepository;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public Beneficiario cadastrar(Beneficiario beneficiario) {

        return beneficiarioRepository.save(beneficiario);
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

    public List<Beneficiario> listarPorGenero(String genero) {
       return beneficiarioRepository.findByGeneroEnumLike(genero);

    }

    public List<Beneficiario> listarPorRaca(String raca) {
        return beneficiarioRepository.findByRacaLike(raca);

    }

    public List<Beneficiario> listarNome(String nome) {
        return beneficiarioRepository.findByNomeContaining(nome);

    }

}
