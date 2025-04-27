package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.entity.Endereco;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByBairroLike(String bairro);

    List<Endereco> findByRuaLike(String rua);

    List<Endereco> findByLogradouroContaining(String logradouro);
}
