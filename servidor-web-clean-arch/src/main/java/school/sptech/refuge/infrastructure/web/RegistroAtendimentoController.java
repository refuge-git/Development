package school.sptech.refuge.infrastructure.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.core.application.dto.registroAtendimento.*;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDiaResponse;
import school.sptech.refuge.core.application.usecase.registroAtendimento.*;

import java.util.List;

@RestController
@RequestMapping("/registros-atendimentos")
public class RegistroAtendimentoController {

    private final AtualizarRegistroAtendimentoUseCase atualizarRegistroAtendimentoUseCase;
    private final CriarRegistroAtendimentoUseCase criarRegistroAtendimentoUseCase;
    private final DeletarRegistroAtendimentoUseCase deletarRegistroAtendimentoUseCase;
    private final ListarTodosRegistroAtendimentoUseCase listarTodosRegistroAtendimentoUseCase;
    private final BuscarRegistroAtendimentoUseCase buscarRegistroAtendimentoUseCase;
    private final ContarBeneficiariosAtendidosNoMesUseCase contarBeneficiariosAtendidosNoMesUseCase;
    private final ContarPresencasPorDiaNoMesUseCase contarPresencasPorDiaNoMesUseCase;
    private final BuscarAtendimentosPorMesUseCase buscarAtendimentosPorMesUseCase;
    private final BuscarServicosPorSemanaUseCase buscarAtendimentosPorSemanaUseCase;
    private final BuscarIndicadoresDashboardUseCase buscarIndicadoresDashboardUseCase;


    public RegistroAtendimentoController(AtualizarRegistroAtendimentoUseCase atualizarRegistroAtendimentoUseCase, CriarRegistroAtendimentoUseCase criarRegistroAtendimentoUseCase, DeletarRegistroAtendimentoUseCase deletarRegistroAtendimentoUseCase, ListarTodosRegistroAtendimentoUseCase listarTodosRegistroAtendimentoUseCase, BuscarRegistroAtendimentoUseCase buscarRegistroAtendimentoUseCase, ContarBeneficiariosAtendidosNoMesUseCase contarBeneficiariosAtendidosNoMesUseCase, ContarPresencasPorDiaNoMesUseCase contarPresencasPorDiaNoMesUseCase, BuscarAtendimentosPorMesUseCase buscarAtendimentosPorMesUseCase, BuscarServicosPorSemanaUseCase buscarAtendimentosPorSemanaUseCase, BuscarIndicadoresDashboardUseCase buscarIndicadoresDashboardUseCase) {
        this.atualizarRegistroAtendimentoUseCase = atualizarRegistroAtendimentoUseCase;
        this.criarRegistroAtendimentoUseCase = criarRegistroAtendimentoUseCase;
        this.deletarRegistroAtendimentoUseCase = deletarRegistroAtendimentoUseCase;
        this.listarTodosRegistroAtendimentoUseCase = listarTodosRegistroAtendimentoUseCase;
        this.buscarRegistroAtendimentoUseCase = buscarRegistroAtendimentoUseCase;
        this.contarBeneficiariosAtendidosNoMesUseCase = contarBeneficiariosAtendidosNoMesUseCase;
        this.contarPresencasPorDiaNoMesUseCase = contarPresencasPorDiaNoMesUseCase;
        this.buscarAtendimentosPorMesUseCase = buscarAtendimentosPorMesUseCase;
        this.buscarAtendimentosPorSemanaUseCase = buscarAtendimentosPorSemanaUseCase;
        this.buscarIndicadoresDashboardUseCase = buscarIndicadoresDashboardUseCase;
    }

    @Operation(
            summary = "Cadastro do registro de atendimento",
            description = "Recebe os dados do registro de atendimento pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro de atendimento cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de registro de atendimento inválidos ou ausente", content = @Content)
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> cadastrar(@Valid @RequestBody RegistroAtendimentoRequestDto dto) {
        RegistroAtendimentoResponseDto dtoSalvo = criarRegistroAtendimentoUseCase.execute(dto);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @Operation(
            summary = "Listar os registros cadastrados",
            description = "Retorna todos os registros cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há registros de atendimento cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<RegistroAtendimentoResponseDto>> listar() {
        List<RegistroAtendimentoResponseDto> registroAtendimentos = listarTodosRegistroAtendimentoUseCase.execute();
        if (registroAtendimentos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(registroAtendimentos);
    }


    @Operation(
            summary = "Buscar registro de atendimento por id",
            description = "Retorna o registro de atendimento completo, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atendimento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> listarPorId(@PathVariable Integer id) {
        RegistroAtendimentoResponseDto dto = buscarRegistroAtendimentoUseCase.execute(id);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Atualização de registro de atendimento",
            description = "Atualiza os dados do registro de atendimento pelo id passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de atendimento atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody RegistroAtendimentoRequestDto dto) {
        RegistroAtendimentoResponseDto dtoAtualizado = atualizarRegistroAtendimentoUseCase.execute(id, dto);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @Operation(
            summary = "Excluir registro de atendimento",
            description = "Exclui o registro de atendimento dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro de atendimento excluida com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarRegistroAtendimentoUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }

    /*@GetMapping("/relatorios/atendimentos-mes")
    public ResponseEntity<byte[]> gerarRelatorioMensal() {
        byte[] pdf = relatorioService.gerarRelatorioMensal();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=relatorio-atendimentos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }*/


    @Operation(
            summary = "Contar beneficiários atendidos no mês atual",
            description = "Retorna a quantidade de beneficiários que tiveram pelo menos um atendimento no mês atual"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    })
    @GetMapping("/relatorios/beneficiarios-atendidos-mes")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Long> contarBeneficiariosNoMes() {
        Long total = contarBeneficiariosAtendidosNoMesUseCase.execute();
        return ResponseEntity.ok(total);
    }

    @Operation(
            summary = "Contagem de presenças por dia do mês atual",
            description = "Retorna uma lista de arrays [dia, qtd_pessoas] representando a quantidade de beneficiários distintos atendidos por dia do mês atual"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    })
    @GetMapping("/relatorios/presencas-por-dia")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PresencaDiaResponse> contarPresencasPorDia(Authentication authentication) {
        String email = authentication.getName();
        PresencaDiaResponse response = contarPresencasPorDiaNoMesUseCase.execute(email);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Retorna a quantidade de atendimentos por mês",
            description = "Lista os atendimentos agrupados por mês para o gráfico do dashboard"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/relatorios/atendimentos-mes")
    @CrossOrigin(origins = "http://localhost:5173") // ajuste conforme porta do seu React
    public ResponseEntity<List<AtendimentosMesDto>> listarAtendimentosPorMes() {
        List<AtendimentosMesDto> atendimentos = buscarAtendimentosPorMesUseCase.obterAtendimentosMes();
        return ResponseEntity.ok(atendimentos);
    }

    @Operation(
            summary = "Quantidade de atendimentos por dia da semana",
            description = "Retorna uma lista de dias da semana e quantidade de atendimentos para a semana atual"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/relatorios/atendimentos-semana")
    @CrossOrigin(origins = "http://localhost:5173") // ajuste conforme porta do seu React
    public ResponseEntity<List<ServicosPorSemanaDto>> listarAtendimentosPorSemana() {
        List<ServicosPorSemanaDto> atendimentos = buscarAtendimentosPorSemanaUseCase.executar();
        return ResponseEntity.ok(atendimentos);
    }

    @Operation(
            summary = "Quantidade de atendimentos por hora no dia atual",
            description = "Retorna a quantidade de atendimentos agrupados por hora, entre 07:00 e 20:00 do dia atual"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/dia")
    @CrossOrigin(origins = "http://localhost:5173") // ajuste conforme sua porta do React
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AtendimentosDiaDto>> getAtendimentosDia() {
        List<AtendimentosDiaDto> atendimentos = buscarAtendimentosPorMesUseCase.obterAtendimentosDia();
        return ResponseEntity.ok(atendimentos);
    }


    @Operation(
            summary = "Quantidade de atendimentos por dia da semana",
            description = "Retorna uma lista de dias da semana e quantidade de atendimentos para a semana atual"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/semana")
    @CrossOrigin(origins = "http://localhost:5173")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AtendimentosSemanaDto>> getAtendimentosSemana() {
        List<AtendimentosSemanaDto> atendimentos = buscarAtendimentosPorMesUseCase.obterAtendimentosSemana();
        return ResponseEntity.ok(atendimentos);
    }

    @Operation(
            summary = "Cadastro em lote de registros de atendimento",
            description = "Recebe uma lista de registros de atendimento e salva todos no banco"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registros de atendimento cadastrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping("/lote")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<RegistroAtendimentoResponseDto>> cadastrarLote(
            @Valid @RequestBody List<RegistroAtendimentoRequestDto> dtos) {
        List<RegistroAtendimentoResponseDto> registrosCriados = criarRegistroAtendimentoUseCase.executeBatch(dtos);
        return ResponseEntity.status(201).body(registrosCriados);
    }



}
