package school.sptech.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Genero;

import java.util.List;
import java.util.Optional;

// !!! MATERIAL DE REFERÊNCIA: SpringBoot com JPA (disponível no moodle) !!!
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {
    // Construção da query JPA: Retorno, nome e parâmetro
    // OBS: Vale ressaltar que o JPA interpreta a query pelo nome dos métodos!!!
    Optional<Beneficiario> findByCpf (String cpf); // Encontrar por CPF
    List<Beneficiario> findByGenero (Genero genero); // Encontrar todos por genero
    List<Beneficiario> findByNomeContainingIgnoreCase (String nome); // Encontrar usuários que contenham esse nome, ignorando maiúsculas e minúsculas
    List<Beneficiario> findByRacaContainingIgnoreCase (String raca); // Encontrar usuários que contenham essa raça, ignorando maiúsculas e minúsculas
    List<Beneficiario> findBySexualidadeContainingIgnoreCase (String sexo);
}
