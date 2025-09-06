package school.sptech.refuge.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.core.application.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.application.usecase.endereco.EnderecoUseCase;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoUseCase useCase;

    public EnderecoController(EnderecoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> cadastrar(@RequestBody EnderecoRequestDto dto) {
        EnderecoResponseDto salvo = useCase.cadastrar(dto);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId;

}
