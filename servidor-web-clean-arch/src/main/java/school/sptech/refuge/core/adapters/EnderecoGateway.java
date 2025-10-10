package school.sptech.refuge.core.adapters;

import school.sptech.refuge.core.domain.endereco.Endereco;
import java.util.List;
import java.util.Optional;

public interface EnderecoGateway {
    Endereco save(Endereco endereco);

    Optional<Endereco> findById(Integer id);

    List<Endereco> findAll();

    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<Endereco> findByBairroContainingIgnoreCase(String bairro);

    List<Endereco> findByTipoLogradouroContainingIgnoreCase(String logradouro);

    List<Endereco> findByNomeLogradouroContainingIgnoreCase(String rua);

    Optional<Endereco> findByCep(String cep);

    Optional<Endereco> findByCepAndNumero(String cep, Integer numero);
}
