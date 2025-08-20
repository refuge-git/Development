package school.sptech.refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.dto.endereco.EnderecoMapper;
import school.sptech.refuge.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.repository.BeneficiarioRepository;
import school.sptech.refuge.repository.EnderecoRepository;

import java.util.List;

@Service
public class EnderecoService {


    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco cadastrar(Endereco endereco) {

        return enderecoRepository.save(endereco);
    }

    public Endereco buscarPorId(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço de id %d não encontrado".formatted(id)));
    }

    public List<Endereco> listar() {

        return enderecoRepository.findAll();
    }

    public Endereco atualizar(Endereco endereco) {
        if (enderecoRepository.existsById(endereco.getId())) {
            endereco.setId(endereco.getId());
            return enderecoRepository.save(endereco);
        } else {
            throw new EnderecoNaoEncontradoException("Endereço de id %d não encontrado".formatted(endereco.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EnderecoNaoEncontradoException("Endereço de id %d não encontrado".formatted(id));
        }

        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o endereço, pois existem registros relacionados a ele.");
        }
    }

    public List<Endereco> listarPorBairro (String bairro) {
        return enderecoRepository.findByBairroContainingIgnoreCase(bairro);

    }

    public List<Endereco> listarPorNomeLogradouro(String rua) {
        return enderecoRepository.findByNomeLogradouroContainingIgnoreCase(rua);

    }

    public List<Endereco> listarPorLogradouro(String logradouro) {
        return enderecoRepository.findByTipoLogradouroContainingIgnoreCase(logradouro);
    }

    public Endereco buscarPorCep(String cep){
        return enderecoRepository.findByCep(cep)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("CEP não encontrado"));
    }

}
