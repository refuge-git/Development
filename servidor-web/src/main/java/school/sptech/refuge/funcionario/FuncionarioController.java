package school.sptech.refuge.funcionario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.usuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listar(){
        List<Funcionario> funcionarios = repository.findAll();

        if(funcionarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(funcionarios);
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario funcionarioCad){
        Optional<Funcionario> funcionarioCpf = repository.findByCpf(funcionarioCad.getCpf());
        Optional<Funcionario> funcionarioEmail = repository.findByEmail(funcionarioCad.getCpf());

        if(funcionarioCpf.isPresent() || funcionarioEmail.isPresent()){
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.status(201).body(repository.save(funcionarioCad));
    }

    // Atualiza usuário por id
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody Funcionario novoFuncionario
    ){
        Optional<Funcionario> funcionarioCPf = repository.findByCpf(novoFuncionario.getCpf());
        if(funcionarioCPf.isPresent() && !funcionarioCPf.get().getId().equals(id)){
            return ResponseEntity.status(409).build();
        }

        Optional<Funcionario> funcionarioBase = repository.findById(id);
        if(funcionarioBase.isPresent()){
            Funcionario funcionario = funcionarioBase.get();

            funcionario.setId(id);
            funcionario.setNome(novoFuncionario.getNome());
            funcionario.setCpf(novoFuncionario.getCpf());
            funcionario.setTelefone(novoFuncionario.getTelefone());
            funcionario.setEmail(novoFuncionario.getEmail());

            return ResponseEntity.status(200).body(repository.save(funcionario));
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> deletar(@PathVariable Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
