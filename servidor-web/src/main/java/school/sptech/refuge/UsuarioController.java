package school.sptech.refuge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios") // URI do servidor
public class UsuarioController {

    @Autowired // SpringBoot se responsabiliza por injetar UsuarioRepository em UsuarioController
    UsuarioRepository repository;


    // Cadastro de usuários
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(
            @RequestBody Usuario userRecived
    ){
        // O Optional é usado nesse caso, pois há a possibilidade de não ser encontrado nenhum usuário
        Optional<Usuario> usuario = repository.findByCpf(userRecived.getCpf());
        if(usuario.isPresent()){
            return ResponseEntity.status(409).build(); // O campo CPF deve ser único, se outro for encontrado na base deve retornar o código 409 (Conflito)
        }

        return ResponseEntity.status(201).body(repository.save(userRecived)); // Código 201 sinaliza que um novo recurso foi criado
    }

    // Retornar todos os usuários cadastrados
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = repository.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(404).build(); // Nenhum usuário encontrado
        }

        return ResponseEntity.status(200).body(usuarios);
    }


    // Retorna o usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> encontrarPorId(
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(usuario.get());
    }

    // Retorna usuários por gênero
    @GetMapping("/genero")
    public ResponseEntity<List<Usuario>> encontrarPorGenero(
        @RequestParam("genero") String genero // Valor passado pela query da URL
    ){
        Genero generoEnum = Genero.valueOf(genero.toUpperCase()); // Convertendo para o tipo Enum
        List<Usuario> usuarios = repository.findByGenero(generoEnum);

        if(usuarios.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(usuarios);
    }

    // Deleta usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        repository.deleteById(id);
        return ResponseEntity.status(200).body(usuario.get());
    }

    // Atualiza usuário por id
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario novoUsuario
    ){
        // Validações contra conflitos de registro único na base de dados
        Optional<Usuario> usuario = repository.findByCpf(novoUsuario.getCpf());
        if(usuario.isPresent() && !usuario.get().getId().equals(id)){
            return ResponseEntity.status(409).build();
        }

        // Verificando existência e atualizando usuário
        Optional<Usuario> veriUser = repository.findById(id);
        if(veriUser.isPresent()){
            Usuario usuarioAtualizado = veriUser.get();

            // Atualizando dados
            usuarioAtualizado.setNome(novoUsuario.getNome());
            usuarioAtualizado.setSexualidade(novoUsuario.getSexualidade());
            usuarioAtualizado.setNomeMae(novoUsuario.getNomeMae());
            usuarioAtualizado.setFotoPerfil(novoUsuario.getFotoPerfil());
            usuarioAtualizado.setNumeroCartao(novoUsuario.getNumeroCartao());
            usuarioAtualizado.setStatus(novoUsuario.getStatus());

            return ResponseEntity.status(200).body(repository.save(usuarioAtualizado));
        }

        return ResponseEntity.status(404).build();
    }
}
