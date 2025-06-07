package school.sptech.refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.repository.TipoAtendimentoRepository;

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
}
