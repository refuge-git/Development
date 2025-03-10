package school.sptech.refuge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Construção: Retorno, nome e parâmetro
    // OBS: Vale ressaltar que o JPA interpreta a query pelo nome dos métodos!!!
    Optional<Usuario> findByCpf (String cpf); // Encontrar por CPF
    List<Usuario> findByGenero (Genero genero); // Encontrar todos por genero
}
