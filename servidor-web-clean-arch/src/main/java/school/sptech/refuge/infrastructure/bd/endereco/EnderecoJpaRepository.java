package school.sptech.refuge.infrastructure.bd.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EnderecoJpaRepository extends JpaRepository<EnderecoEntity, Integer> {
    List<EnderecoEntity> findByBairroContainingIgnoreCase(String bairro);
    List<EnderecoEntity> findByTipoLogradouroContainingIgnoreCase(String logradouro);
    List<EnderecoEntity> findByNomeLogradouroContainingIgnoreCase(String rua);
    Optional<EnderecoEntity> findByCep(String cep);
    Optional<EnderecoEntity> findByCepAndNumero(String cep, Integer numero);
}
