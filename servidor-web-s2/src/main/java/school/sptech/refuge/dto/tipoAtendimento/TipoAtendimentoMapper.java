package school.sptech.refuge.dto.tipoAtendimento;

import school.sptech.refuge.entity.TipoAtendimento;

import java.util.ArrayList;
import java.util.List;

public class TipoAtendimentoMapper {
    public TipoAtendimento toEntity(TipoAtendimentoRequestDto dto){
        TipoAtendimento entity = new TipoAtendimento();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setDt_criacao(dto.getDt_criacao());

        return entity;
    }

    public TipoAtendimentoResponseDto toDto(TipoAtendimento entity){
        TipoAtendimentoResponseDto dto = new TipoAtendimentoResponseDto();

        dto.setId(entity.getId_TipoAtendimento());
        dto.setNome(entity.getNome());

        return dto;
    }

    public List<TipoAtendimentoResponseDto> toListDto(List<TipoAtendimento> atendimentos){
        List<TipoAtendimentoResponseDto> responseDtos = new ArrayList<>();

        for(TipoAtendimento at : atendimentos){
            responseDtos.add(toDto(at));
        }

        return responseDtos;
    }
}
