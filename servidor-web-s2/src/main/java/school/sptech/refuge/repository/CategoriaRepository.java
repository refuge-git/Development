package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
