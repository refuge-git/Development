package school.sptech.refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.repository.TipoAtendimentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAtendimentoService {
    @Autowired
    private TipoAtendimentoRepository repository;

    private TipoAtendimentoMapper mapper = new TipoAtendimentoMapper();

    // CREATE
    public TipoAtendimentoResponseDto criar(TipoAtendimentoRequestDto dto){
        TipoAtendimento response = repository.save(mapper.toEntity(dto));
        return mapper.toDto(response);
    }

    // READ
    public List<TipoAtendimentoResponseDto> listarTodos(){
        return mapper.toListDto(repository.findAll());
    }

    public TipoAtendimentoResponseDto listarPorId(Integer id){
        Optional<TipoAtendimento> atendimento = repository.findById(id);

        if(atendimento.isEmpty()){
            throw new EntidadeNaoEncontradaException("Tipo de atendimento com id %d não encontrado".formatted(id));
        }

        return mapper.toDto(atendimento.get());
    }

    // UPDATE
    public TipoAtendimentoResponseDto atualizar(Integer id, TipoAtendimentoRequestDto dto){
        TipoAtendimento tipoAtendimento = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de atendimento com id %d não encontrado".formatted(id)));

        tipoAtendimento.setId_TipoAtendimento(id);
        tipoAtendimento.setNome(dto.getNome());
        tipoAtendimento.setDescricao(dto.getDescricao());
        tipoAtendimento.setDt_criacao(dto.getDt_criacao());

        return mapper.toDto(repository.save(tipoAtendimento));
    }

    public void deletar(Integer id){
        if (!repository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Tipo de atendimento com id %d não encontrado".formatted(id));
        }

        repository.deleteById(id);
    }
}
