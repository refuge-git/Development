package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.antes.entity.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findByBairroContainingIgnoreCase(String bairro);

    List<Endereco> findByTipoLogradouroContainingIgnoreCase(String logradouro);

    List<Endereco> findByNomeLogradouroContainingIgnoreCase(String rua);

    Optional<Endereco> findByCep(String cep);
}
