package school.sptech.refuge.antes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.antes.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<Categoria> findAllByNome(String nome);
}
