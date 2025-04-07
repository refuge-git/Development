package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco cadastrar(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public Optional<Endereco> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Endereco> atualizar(Integer id, Endereco novoEndereco) {
        return repository.findById(id).map(endereco -> {
            endereco.setCep(novoEndereco.getCep());
            endereco.setRua(novoEndereco.getRua());
            endereco.setBairro(novoEndereco.getBairro());
            endereco.setLogradouro(novoEndereco.getLogradouro());
            endereco.setNumero(novoEndereco.getNumero());
            return repository.save(endereco);
        });
    }

}
