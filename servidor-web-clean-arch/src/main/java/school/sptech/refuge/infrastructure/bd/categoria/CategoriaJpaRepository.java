package school.sptech.refuge.infrastructure.bd.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<CategoriaEntity> findAllByNome(String nome);
}
