package school.sptech.refuge.infrastructure.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.refuge.core.application.dto.registroAtendimento.IndicadoresDashboardDto;
import school.sptech.refuge.core.application.usecase.registroAtendimento.BuscarIndicadoresDashboardUseCase;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/indicadores")
public class IndicadoresController {

    private final BuscarIndicadoresDashboardUseCase buscarIndicadoresDashboardUseCase;

    public IndicadoresController(BuscarIndicadoresDashboardUseCase buscarIndicadoresDashboardUseCase) {
        this.buscarIndicadoresDashboardUseCase = buscarIndicadoresDashboardUseCase;
    }

    @GetMapping
    public ResponseEntity<IndicadoresDashboardDto> buscarIndicadores() {
        System.out.println("🚀 Recebendo requisição em /indicadores...");
        IndicadoresDashboardDto indicadores = buscarIndicadoresDashboardUseCase.executar();
        System.out.println("✅ Indicadores retornados: " + indicadores);
        return ResponseEntity.ok(indicadores);
    }

}