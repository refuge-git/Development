package school.sptech.refuge.infrastructure.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.core.application.dto.beneficiario.*;
import school.sptech.refuge.core.application.usecase.beneficiario.*;
import school.sptech.refuge.core.domain.beneficiario.LocalEnum;
import school.sptech.refuge.core.domain.paginacao.Page;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;
import school.sptech.refuge.infrastructure.config.bucketS3.S3UploadService;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beneficiarios") // URI do servidor
public class BeneficiarioController {

    @Autowired
    private S3UploadService s3UploadService;
    private final CriarBeneficiarioUseCase criarBeneficiarioUseCase;
    private final ListarTodosBeneficiarioUseCase listarTodosBeneficiarioUseCase;
    private final BuscarBeneficiarioUseCase buscarBeneficiarioUseCase;
    private final AtualizarBeneficiarioUseCase atualizarBeneficiarioUseCase;
    private final DeletarBeneficiarioUseCase deletarBeneficiarioUseCase;
    private final ListarBeneficiarioPorRacaUseCase listarBeneficiarioPorRacaUseCase;
    private final ListarBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusUseCase;
    private final ListarBeneficiariosPorNomeRegistroOuSocialUseCase listarBeneficiarioPorNomeUse;
    private final ListarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase;
    private final ListagemBeneficiarioUseCase listagemBeneficiarioUseCase;
    private final PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase;
    private final PaginarListagemBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusPaginado;


    public BeneficiarioController(PaginarListagemBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusPaginado, PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase, ListagemBeneficiarioUseCase listagemBeneficiarioUseCase, ListarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase, ListarBeneficiariosPorNomeRegistroOuSocialUseCase listarBeneficiarioPorNomeUse, ListarBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusUseCase, ListarBeneficiarioPorRacaUseCase listarBeneficiarioPorRacaUseCase, DeletarBeneficiarioUseCase deletarBeneficiarioUseCase, AtualizarBeneficiarioUseCase atualizarBeneficiarioUseCase, BuscarBeneficiarioUseCase buscarBeneficiarioUseCase, ListarTodosBeneficiarioUseCase listarTodosBeneficiarioUseCase, CriarBeneficiarioUseCase criarBeneficiarioUseCase, S3UploadService s3UploadService) {
        this.listarBeneficiarioPorStatusPaginado = listarBeneficiarioPorStatusPaginado;
        this.paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase = paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase;
        this.listagemBeneficiarioUseCase = listagemBeneficiarioUseCase;
        this.listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase = listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase;
        this.listarBeneficiarioPorNomeUse = listarBeneficiarioPorNomeUse;
        this.listarBeneficiarioPorStatusUseCase = listarBeneficiarioPorStatusUseCase;
        this.listarBeneficiarioPorRacaUseCase = listarBeneficiarioPorRacaUseCase;
        this.deletarBeneficiarioUseCase = deletarBeneficiarioUseCase;
        this.atualizarBeneficiarioUseCase = atualizarBeneficiarioUseCase;
        this.buscarBeneficiarioUseCase = buscarBeneficiarioUseCase;
        this.listarTodosBeneficiarioUseCase = listarTodosBeneficiarioUseCase;
        this.criarBeneficiarioUseCase = criarBeneficiarioUseCase;
        this.s3UploadService = s3UploadService;
    }

    @Operation(
            summary = "Cadastro de beneficiário",
            description = "Recebe os dados do beneficiário pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiário cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de beneficiário inválidos ou ausente", content = @Content)
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    // NOVO ATRIBUTO PARA SALVAR ENDEREÇO DE IMAGEM (id do bucket)
    public ResponseEntity<BeneficarioListDto> cadastrar(@Valid @RequestBody BeneficiarioRequestDto dto) {
        BeneficarioListDto criado = criarBeneficiarioUseCase.execute(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @Operation(
            summary = "Listar beneficiários",
            description = "Retorna todos os beneficiários cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há beneficiários cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listar() {
        List<BeneficarioListDto> dtos = listarTodosBeneficiarioUseCase.execute();

        if (dtos.isEmpty()) return ResponseEntity.noContent().build();

        // Mapeia a lista para adicionar a URL da imagem usando o S3UploadService
        /*List<BeneficarioListDto> dtosComImagem = dtos.stream()
                .peek(dto -> {
                    if (dto.getFotoPerfil() != null && !dto.getFotoPerfil().isEmpty()) {
                        String imagemUrl = s3UploadService.getFile(dto.getFotoPerfil());
                        dto.setImagemUrl(imagemUrl);
                    }
                })
                .collect(Collectors.toList());*/

        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Buscar beneficiário por id",
            description = "Retorna o beneficiário completo, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BeneficarioListDto> listarPorId(@PathVariable Integer id) {
        BeneficarioListDto dto = buscarBeneficiarioUseCase.execute(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Atualização de beneficiário",
            description = "Atualiza os dados do beneficiário pelo id passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BeneficarioListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody BeneficiarioAtualizacaoDto dto) {
        BeneficarioListDto dtoAtualizado = atualizarBeneficiarioUseCase.execute(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }


    @Operation(
            summary = "Beneficiários por status",
            description = "Listar todos os beneficiários pelo status especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por status encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o status especificado foi encontrado", content = @Content)
    })
    @GetMapping("/statusteste")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorStatus(@RequestParam String raca) {
        List<BeneficarioListDto> dtos = listarBeneficiarioPorRacaUseCase.execute(raca);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Beneficiários por raça",
            description = "Listar todos os beneficiários pela raça especificada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por raça encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com a raça especificada foi encontrado", content = @Content)
    })
    @GetMapping("/raca")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorRaca(@RequestParam String raca) {
        List<BeneficarioListDto> dtos = listarBeneficiarioPorRacaUseCase.execute(raca);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Beneficiários por nome de registro.",
            description = "Listar todos os beneficiários pelo nome de registro especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por nome de registro encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o nome de registro especificado foi encontrado", content = @Content)
    })
    @GetMapping("/nome")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarContendoNomeRegistroOuNomeSocial(@RequestParam String nome) {
        List<BeneficarioListDto> dtos = listarBeneficiarioPorNomeUse.execute(nome);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Exclusão de beneficiário",
            description = "Excluir beneficiário, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beneficiários excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarBeneficiarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/opcoes_racas")
    @Operation(
            summary = "Listar opções de raças",
            description = "Retorna todas as opções possíveis de raça para beneficiário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opções de raças retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Map<String, String>>> listarOpcoesRacas() {
        List<Map<String, String>> racas = Arrays.stream(RacaEnum.values())
                .map(racaEnum -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", racaEnum.name());
                    map.put("descricao", racaEnum.getDescricaoRaca());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(racas);
    }

    @GetMapping("/opcoes_sexo")
    @Operation(
            summary = "Listar opções de sexo",
            description = "Retorna todas as opções possíveis de sexo para beneficiário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opções de sexo retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Map<String, String>>> listarOpcoesSexo() {
        List<Map<String, String>> sexos = Arrays.stream(SexoEnum.values())
                .map(sexoEnum -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", sexoEnum.name());
                    map.put("descricao", sexoEnum.getDescricaoSexo());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(sexos);
    }

    @GetMapping("/opcoes_local")
    @Operation(
            summary = "Listar opções de local",
            description = "Retorna todas as opções possíveis de local para beneficiário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opções de local retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Map<String, String>>> listarOpcoesLocal() {
        List<Map<String, String>> locais = Arrays.stream(LocalEnum.values())
                .map(localEnum -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", localEnum.name());
                    map.put("descricao", localEnum.getDescricaoLocal());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficiarioStatusDto>> listarTodosStatus() {
        List<BeneficarioListDto> beneficiarios = listarTodosBeneficiarioUseCase.execute();

        if (beneficiarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<BeneficiarioStatusDto> dtos = beneficiarios.stream()
                .map(b -> new BeneficiarioStatusDto(
                        b.getNomeRegistro(),
                        b.getStatus() != null ? b.getStatus() : "INATIVO"
                ))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/frequencia-dia-semana")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorFrequenciaNoDiaDaSemana() {
        int diaSemana = LocalDate.now().getDayOfWeek().getValue();
        List<BeneficarioListDto> dtos = listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase.execute(diaSemana);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Listando quantidade limitada de beneficiário.",
            description = "Listar quantidade limitada dos beneficiários"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário  foi encontrado", content = @Content)
    })

    @GetMapping("/listar-page")
    public Page<BeneficarioListDto> listarBeneficiarios(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return listagemBeneficiarioUseCase.execute(page, size);
    }


    @Operation(
            summary = "Listando quantidade limitada de beneficiário por frequencia.",
            description = "Listar quantidade limitada dos beneficiários por frequencia."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário  foi encontrado", content = @Content)
    })

    @GetMapping("/listar-page-frequencia")
    public ResponseEntity<Page<BeneficiarioFrequenciaDto>> listarBeneficiariosPorFrequencia(
            @RequestParam int diaSemana,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam (defaultValue = "") String search) {

        if (diaSemana < 1 || diaSemana > 7) {
            return ResponseEntity.badRequest().build();
        }

        Page<BeneficiarioFrequenciaDto> resultado = paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase.execute(diaSemana, page, size, search);
        if (resultado.getItems() == null || resultado.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/status/page")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Page<BeneficiarioStatusDto>> listarBeneficiarioPorStatusPaginado(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam (defaultValue = "") String search
    ) {
        Page<BeneficiarioStatusDto> resultado = listarBeneficiarioPorStatusPaginado.execute(search, status, page, size);
        if (resultado.getItems() == null || resultado.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultado);

    }


}
