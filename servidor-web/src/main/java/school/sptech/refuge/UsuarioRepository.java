package school.sptech.refuge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Construção: Retorno, nome e parâmetro
    Optional<Usuario> findByCpf (String cpf); // Encontrar por CPF
}
