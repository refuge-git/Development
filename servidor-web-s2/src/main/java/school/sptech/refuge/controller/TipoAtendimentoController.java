package school.sptech.refuge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.service.TipoAtendimentoService;

@RestController
@RequestMapping("/atendimentos")
public class TipoAtendimentoController {
    @Autowired
    private TipoAtendimentoService service;

    // CREATE
    @PostMapping("/{id}")
    public ResponseEntity<TipoAtendimentoResponseDto> criar(
            @RequestBody TipoAtendimentoRequestDto dto
            ){
        return ResponseEntity.status(201).body(service.criar(dto));
    }
}
