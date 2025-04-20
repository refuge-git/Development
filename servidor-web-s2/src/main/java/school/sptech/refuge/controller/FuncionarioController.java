package school.sptech.refuge.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.funcionario.*;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.repository.FuncionarioRepository;
import school.sptech.refuge.service.FuncionarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

//    @PostMapping
//    public ResponseEntity<FuncionarioListDto> cadastrar(@Valid @RequestBody FuncionarioRequestDto dto) {
//        Funcionario funcionario = FuncionarioMapper.toEntity(dto);
//        Funcionario funcionarioCadastrado = funcionarioService.cadastrar(funcionario);
//        FuncionarioListDto dtoSalvo = FuncionarioMapper.toListagemDto(funcionarioCadastrado);
//        return ResponseEntity.status(201).body(dtoSalvo);
//    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid FuncionarioRequestDto funcionarioRequestDto){

        final Funcionario novoFuncionario = FuncionarioMapper.of(funcionarioRequestDto);
        this.funcionarioService.criar(novoFuncionario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public  ResponseEntity<FuncionarioTokenDto> login (@RequestBody FuncionarioLoginDto funcionarioLoginDto){

        final Funcionario funcionario = FuncionarioMapper.of(funcionarioLoginDto);
        FuncionarioTokenDto funcionarioTokenDto = this.funcionarioService.autenticar(funcionario);

        return ResponseEntity.status(200).body(funcionarioTokenDto);
    }

//    @GetMapping
//    public ResponseEntity<List<FuncionarioListDto>> listar() {
//
//        List<Funcionario> funcionarios = funcionarioService.listar();
//
//        if (funcionarios.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//
//        List<FuncionarioListDto> dtos = FuncionarioMapper.toListagemDtos(funcionarios);
//        return ResponseEntity.status(200).body(dtos);
//    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FuncionarioListDto>> listarTodos(){

        List<FuncionarioListDto> funcionariosEncontrados = this.funcionarioService.listarTodos();

        if(funcionariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(funcionariosEncontrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioListDto> listarPorId(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        FuncionarioListDto dto = FuncionarioMapper.toListagemDto(funcionario);
        return ResponseEntity.status(200).body(dto);
    }



    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody FuncionarioAtualizacaoDto dto) {
        Funcionario funcionario = FuncionarioMapper.toEntity(dto, id);
        Funcionario funcionarioAtualizado = funcionarioService.atualizar(funcionario);
        FuncionarioListDto dtoAtualizado = FuncionarioMapper.toListagemDto(funcionarioAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        funcionarioService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }

}
