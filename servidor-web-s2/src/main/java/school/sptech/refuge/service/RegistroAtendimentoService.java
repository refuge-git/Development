package school.sptech.refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.refuge.dto.registroAtendimento.RegistroAtendimentoMapper;
import school.sptech.refuge.dto.registroAtendimento.RegistroAtendimentoRequestDto;
import school.sptech.refuge.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.RegistroAtendimento;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.repository.BeneficiarioRepository;
import school.sptech.refuge.repository.RegistroAtendimentoRepository;
import school.sptech.refuge.repository.TipoAtendimentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroAtendimentoService {
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private TipoAtendimentoRepository tipoAtendimentoRepository;

    @Autowired
    private RegistroAtendimentoRepository repository;

    private RegistroAtendimentoMapper mapper = new RegistroAtendimentoMapper();

    // CREATE
    public RegistroAtendimentoResponseDto criar(RegistroAtendimentoRequestDto dto) {
        TipoAtendimento tipo = tipoAtendimentoRepository.findById(dto.getIdTipoAtendimento())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de atendimento com id %d não encontrado".formatted(dto.getIdTipoAtendimento())));

        Beneficiario beneficiario = beneficiarioRepository.findById(dto.getIdBeneficiario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Beneficiário com id %d não encontrado".formatted(dto.getIdBeneficiario())));

        RegistroAtendimento novo = new RegistroAtendimento();
        novo.setData_hora(dto.getData_hora());
        novo.setTipoAtendimento(tipo);
        novo.setBeneficiario(beneficiario);

        return mapper.toDto(repository.save(novo));
    }

    // READ - TODOS
    public List<RegistroAtendimentoResponseDto> listarTodos() {
        List<RegistroAtendimento> lista = repository.findAll();
        return mapper.toListDto(lista);
    }

    // READ - POR ID
    public RegistroAtendimentoResponseDto buscarPorId(Integer id) {
        Optional<RegistroAtendimento> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Registro de atendimento com id %d não encontrado".formatted(id));
        }
        return mapper.toDto(opt.get());
    }

    // UPDATE
    public RegistroAtendimentoResponseDto atualizar(Integer id, RegistroAtendimentoRequestDto dto) {
        RegistroAtendimento existente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Registro de atendimento com id %d não encontrado".formatted(id)));

        TipoAtendimento tipo = tipoAtendimentoRepository.findById(dto.getIdTipoAtendimento())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de atendimento com id %d não encontrado".formatted(dto.getIdTipoAtendimento())));

        Beneficiario beneficiario = beneficiarioRepository.findById(dto.getIdBeneficiario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Beneficiário com id %d não encontrado".formatted(dto.getIdBeneficiario())));


        RegistroAtendimento atualizado = mapper.toEntity(dto, tipo, beneficiario);
        atualizado.setId(id);

        return mapper.toDto(repository.save(atualizado));
    }

    // DELETE
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Registro de atendimento com id %d não encontrado".formatted(id));
        }
        repository.deleteById(id);
    }
}
