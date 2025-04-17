package school.sptech.refuge.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.funcionario.FuncionarioAtualizacaoDto;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.dto.funcionario.FuncionarioMapper;
import school.sptech.refuge.dto.funcionario.FuncionarioRequestDto;
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

    @PostMapping
    public ResponseEntity<FuncionarioListDto> cadastrar(@Valid @RequestBody FuncionarioRequestDto dto) {
        Funcionario funcionario = FuncionarioMapper.toEntity(dto);
        Funcionario funcionarioCadastrado = funcionarioService.cadastrar(funcionario);
        FuncionarioListDto dtoSalvo = FuncionarioMapper.toListagemDto(funcionarioCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioListDto>> listar() {

        List<Funcionario> funcionarios = funcionarioService.listar();

        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<FuncionarioListDto> dtos = FuncionarioMapper.toListagemDtos(funcionarios);
        return ResponseEntity.status(200).body(dtos);
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
