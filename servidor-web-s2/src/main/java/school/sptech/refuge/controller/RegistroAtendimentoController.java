package school.sptech.refuge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.registroAtendimento.RegistroAtendimentoRequestDto;
import school.sptech.refuge.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.service.RegistroAtendimentoService;

import java.util.List;

@RestController
@RequestMapping("/registros-atendimentos")
public class RegistroAtendimentoController {

    @Autowired
    private RegistroAtendimentoService service;

    // CREATE
    @Operation(
            summary = "Criação de um registro de atendimento",
            description = "Cria um novo registro de atendimento a partir do payload passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro de atendimento criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> criar(
            @RequestBody RegistroAtendimentoRequestDto dto
    ) {
        return ResponseEntity.status(201).body(service.criar(dto));
    }

    // READ
    @Operation(
            summary = "Listagem de registros de atendimento",
            description = "Lista todos os registros de atendimentos cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<RegistroAtendimentoResponseDto>> listarTodos() {
        return ResponseEntity.status(200).body(service.listarTodos());
    }

    @Operation(
            summary = "Exibição de um registro de atendimento",
            description = "Busca um registro de atendimento pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de atendimento encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    // UPDATE
    @Operation(
            summary = "Atualização de um registro de atendimento",
            description = "Atualiza um registro de atendimento com base no ID e no payload fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de atendimento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @PutMapping("/atualizar/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody RegistroAtendimentoRequestDto dto
    ) {
        return ResponseEntity.status(200).body(service.atualizar(id, dto));
    }

    // DELETE
    @Operation(
            summary = "Remoção de um registro de atendimento",
            description = "Remove um registro de atendimento pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro de atendimento removido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
