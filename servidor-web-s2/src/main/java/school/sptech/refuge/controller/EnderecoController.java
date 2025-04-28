package school.sptech.refuge.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.endereco.EnderecoAtualizacaoDto;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.endereco.EnderecoMapper;
import school.sptech.refuge.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.service.BeneficiarioService;
import school.sptech.refuge.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {


    private final EnderecoService enderecoService;
    private final BeneficiarioService beneficiarioService;

    public EnderecoController(EnderecoService enderecoService, BeneficiarioService beneficiarioService) {
        this.enderecoService = enderecoService;
        this.beneficiarioService = beneficiarioService;
    }

    // ARRUMAR POSTERIORMENTE!!!
//    @PostMapping
//    public ResponseEntity<EnderecoListDto> cadastrar(@Valid @RequestBody EnderecoRequestDto dto) {
//        Endereco endereco = EnderecoMapper.toEntity(dto, beneficiario);
//        Endereco enderecoCadastrado = enderecoService.cadastrar(endereco);
//        EnderecoListDto dtoSalvo = EnderecoMapper.toListagemDto(enderecoCadastrado);
//        return ResponseEntity.status(201).body(dtoSalvo);
//    }

    @GetMapping
    public ResponseEntity<List<EnderecoListDto>> listar() {
        List<Endereco> enderecos = enderecoService.listar();
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoListDto> listarPorId(@PathVariable Integer id) {
        Endereco endereco = enderecoService.buscarPorId(id);
        EnderecoListDto dto = EnderecoMapper.toListagemDto(endereco);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EnderecoAtualizacaoDto dto) {
        Beneficiario beneficiario = beneficiarioService.buscarPorId(id);
        Endereco endereco = EnderecoMapper.toEntity(dto, beneficiario);
        Endereco enderecoAtualizado = enderecoService.atualizar(endereco);
        EnderecoListDto dtoAtualizado = EnderecoMapper.toListagemDto(enderecoAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        enderecoService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/bairro")
    public ResponseEntity<List<EnderecoListDto>> listarPorBairro(@RequestParam String bairro) {
        List<Endereco> enderecos = enderecoService.listarPorBairro(bairro);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/rua")
    public ResponseEntity<List<EnderecoListDto>> listarPorRua(@RequestParam String rua) {
        List<Endereco> enderecos = enderecoService.listarPorRua(rua);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/logradouro")
    public ResponseEntity<List<EnderecoListDto>> listarPorLogradouro(@RequestParam String logradouro) {
        List<Endereco> enderecos = enderecoService.listarPorLogradouro(logradouro);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }
    
}
