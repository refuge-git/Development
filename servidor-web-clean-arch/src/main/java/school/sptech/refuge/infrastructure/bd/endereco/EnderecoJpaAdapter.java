package school.sptech.refuge.infrastructure.bd.endereco;

import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.domain.endereco.Endereco;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoJpaAdapter implements EnderecoGateway {

    private final EnderecoJpaRepository repository;

    public EnderecoJpaAdapter(EnderecoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Endereco save(Endereco endereco) {
        EnderecoEntity entity = EnderecoMapper.toEntity(endereco);
        EnderecoEntity salvo = repository.save(entity);
        return EnderecoMapper.toDomain(salvo);
    }

    @Override
    public Optional<Endereco> findById(Integer id) {
        return repository.findById(id).map(EnderecoMapper::toDomain);
    }

    @Override
    public List<Endereco> findAll() {
        return repository.findAll().stream().map(EnderecoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Endereco> findByBairroContainingIgnoreCase(String bairro) {
        return repository.findByBairroContainingIgnoreCase(bairro).stream().map(EnderecoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Endereco> findByTipoLogradouroContainingIgnoreCase(String logradouro) {
        return repository.findByTipoLogradouroContainingIgnoreCase(logradouro).stream().map(EnderecoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Endereco> findByNomeLogradouroContainingIgnoreCase(String rua) {
        return repository.findByNomeLogradouroContainingIgnoreCase(rua).stream().map(EnderecoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Endereco> findByCep(String cep) {
        return repository.findByCep(cep).map(EnderecoMapper::toDomain);
    }

    @Override
    public Optional<Endereco> findByCepAndNumero(String cep, Integer numero) {
        return repository.findByCepAndNumero(cep, numero)
                .map(EnderecoMapper::toDomain);
    }


}
