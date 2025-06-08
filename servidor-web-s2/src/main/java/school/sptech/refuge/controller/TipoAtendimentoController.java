package school.sptech.refuge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.funcionario.FuncionarioTokenDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.service.TipoAtendimentoService;

import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class TipoAtendimentoController {
    @Autowired
    private TipoAtendimentoService service;

    // CREATE
    @Operation(
            summary = "Criação de um tipo de atendimento",
            description = "Cria um novo tipo de atendimento a partir do payload passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de atendimento cadastro com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class)))
    })
    @PostMapping()
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoAtendimentoResponseDto> criar(
            @RequestBody TipoAtendimentoRequestDto dto
            ){
        return ResponseEntity.status(201).body(service.criar(dto));
    }

    // READ
    @Operation(
            summary = "Listagem de tipo de atendimento",
            description = "Lista todos os tipos de atendimentos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atendimento cadastro com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class)))
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<TipoAtendimentoResponseDto>> listarTodos(){
        return ResponseEntity.status(200).body(service.listarTodos());
    }

    @Operation(
            summary = "Exibição de um tipo de atendimento",
            description = "Lista um tipo de atendimento a partir do id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atendimento cadastro com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoAtendimentoResponseDto> listarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.listarPorId(id));
    }


    // UPDATE
    @Operation(
        summary = "Atualização de um tipo de atendimento",
        description = "Atualiza um tipo de atendimento a partir do id e payload passados"
            )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atendimento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class))
            )
    })
    @PutMapping("/atualizar/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoAtendimentoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody TipoAtendimentoRequestDto dto
    ) {
        return ResponseEntity.status(200).body(service.atualizar(id, dto));
    }

    // DELETE
    @Operation(
            summary = "Remoção de um tipo de atendimento",
            description = "Remove um tipo de atendimento a partir do id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de atendimento removido com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
