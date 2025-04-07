package school.sptech.refuge.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Genero;
import school.sptech.refuge.repository.BeneficiarioRepository;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios") // URI do servidor
public class BeneficiarioController {

    @Autowired // SpringBoot se responsabiliza por injetar UsuarioRepository em UsuarioController
    BeneficiarioRepository repository;







    ///  ENDPOINTS DO TIPO: POST

    // Cadastro de usuários
    @PostMapping
    public ResponseEntity<Beneficiario> cadastrar(
            @RequestBody Beneficiario userRecived
    ){
        // O Optional é usado nesse caso, pois há a possibilidade de não ser encontrado nenhum usuário
        Optional<Beneficiario> usuario = repository.findByCpf(userRecived.getCpf());
        if(usuario.isPresent()){
            return ResponseEntity.status(409).build(); // O campo CPF deve ser único, se outro for encontrado na base deve retornar o código 409 (Conflito)
        }

        return ResponseEntity.status(201).body(repository.save(userRecived)); // Código 201 sinaliza que um novo recurso foi criado
    }






    /// ENDPOINTS DO TIPO: GET

    // Retornar todos os beneficiarios cadastrados
    @GetMapping("/listar")
    public ResponseEntity<List<Beneficiario>> listar(){
        List<Beneficiario> beneficiarios = repository.findAll();

        if (beneficiarios.isEmpty()) {
            return ResponseEntity.status(404).build(); // Nenhum beneficiario encontrado
        }

        return ResponseEntity.status(200).body(beneficiarios);
    }

    // Retorna o usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> encontrarPorId(
            @PathVariable Integer id
    ){
        Optional<Beneficiario> beneficiario = repository.findById(id);

        if(beneficiario.isEmpty()){
            return ResponseEntity.status(404).build(); // Nenhum usuário encontrado
        }

        return ResponseEntity.status(200).body(beneficiario.get());
    }

    // Retorna usuários por gênero
    @GetMapping("/genero")
    public ResponseEntity<List<Beneficiario>> encontrarPorGenero(
        @RequestParam("genero") String genero // Valor passado pela query da URL
    ){
        /* Essa conversão é necessária, pois o hibernate não converter uma String
           para o tipo Enum automaticamente, que é o atributo da entidade usuário */
        Genero generoEnum = Genero.valueOf(genero.toUpperCase()); // Convertendo para o tipo Enum
        List<Beneficiario> usuarios = repository.findByGenero(generoEnum);

        if(usuarios.isEmpty()){
            return ResponseEntity.status(404).build(); // Nenhum usuário encontrado
        }

        return ResponseEntity.status(200).body(usuarios);
    }

    // Encontrar usuários por nome
    @GetMapping("/nome")
    public ResponseEntity<List<Beneficiario>> encontrarPorNome(
            @RequestParam("nome") String nome // Valor passado pela query da URL
    ){
        // Explicação da query em: UsuarioRepository
        List<Beneficiario> beneficiarios = repository.findByNomeContainingIgnoreCase(nome);

        if(beneficiarios.isEmpty()){
            return ResponseEntity.status(404).build(); // Nenhum usuário encontrado
        }

        return ResponseEntity.status(200).body(beneficiarios);
    }

    // Encontrar usuários por raça
    // Este endpoint segue o mesmo princípio do anterior!
    @GetMapping("/raca")
    public ResponseEntity<List<Beneficiario>> encontrarPorRaca(
            @RequestParam("raca") String raca // Valor passado pela query da URL
    ){
        // Explicação da query em: UsuarioRepository
        List<Beneficiario> beneficiarios = repository.findByRacaContainingIgnoreCase(raca);

        if(beneficiarios.isEmpty()){
            return ResponseEntity.status(404).build(); // Nenhum usuário encontrado
        }

        return ResponseEntity.status(200).body(beneficiarios);
    }

    @GetMapping("/sexualidade")
    public ResponseEntity<List<Beneficiario>> encontrarPorSexualidade(
            @RequestParam("sexualidade") String sexo
            ){
        List<Beneficiario> beneficiarios = repository.findBySexualidadeContainingIgnoreCase(sexo);

        if(beneficiarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(beneficiarios);
    }






    ///  ENDPOINTS DO TIPO: DELETE

    // Deleta usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Beneficiario> deletarBeneficiario(
            @PathVariable Integer id
    ){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }






    ///  ENDPOINTS DO TIPO: PUT

    // Atualiza usuário por id
    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> atualizarBeneficiario(
            @PathVariable Integer id,
            @RequestBody Beneficiario novoBeneficiario
    ){
        // Validações contra conflitos de registro único na base de dados
        Optional<Beneficiario> beneficiario = repository.findByCpf(novoBeneficiario.getCpf());
        if(beneficiario.isPresent() && !beneficiario.get().getId().equals(id)){
            return ResponseEntity.status(409).build();
        }

        // Verificando existência e atualizando usuário
        Optional<Beneficiario> veriBeneficiario = repository.findById(id);
        if(veriBeneficiario.isPresent()){
            Beneficiario beneficiarioAtualizado = veriBeneficiario.get();

            // Atualizando dados
            beneficiarioAtualizado.setNome(novoBeneficiario.getNome());
            beneficiarioAtualizado.setSexualidade(novoBeneficiario.getSexualidade());
            beneficiarioAtualizado.setNomeMae(novoBeneficiario.getNomeMae());
            beneficiarioAtualizado.setFotoPerfil(novoBeneficiario.getFotoPerfil());
            beneficiarioAtualizado.setNumeroCartao(novoBeneficiario.getNumeroCartao());
            beneficiarioAtualizado.setStatus(novoBeneficiario.getStatus());

            return ResponseEntity.status(200).body(repository.save(beneficiarioAtualizado));
        }

        return ResponseEntity.status(404).build();
    }
}
