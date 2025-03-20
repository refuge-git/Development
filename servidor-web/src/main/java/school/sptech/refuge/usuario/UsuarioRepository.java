package school.sptech.refuge.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.usuario.entity.Genero;
import school.sptech.refuge.usuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

// !!! MATERIAL DE REFERÊNCIA: SpringBoot com JPA (disponível no moodle) !!!
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Construção da query JPA: Retorno, nome e parâmetro
    // OBS: Vale ressaltar que o JPA interpreta a query pelo nome dos métodos!!!
    Optional<Usuario> findByCpf (String cpf); // Encontrar por CPF
    List<Usuario> findByGenero (Genero genero); // Encontrar todos por genero
    List<Usuario> findByNomeContainingIgnoreCase (String nome); // Encontrar usuários que contenham esse nome, ignorando maiúsculas e minúsculas
    List<Usuario> findByRacaContainingIgnoreCase (String raca); // Encontrar usuários que contenham essa raça, ignorando maiúsculas e minúsculas
    List<Usuario> findBySexualidadeContainingIgnoreCase (String sexo);
}
