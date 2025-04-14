package school.sptech.refuge.dto.TipoAtendimento;

import school.sptech.refuge.entity.TipoAtendimento;

public class TipoAtendimentoMapper {


    public static TipoAtendimentoListDto toDTO(TipoAtendimento tipoAtendimento) {
        if (tipoAtendimento == null) {
            return null;
        }
        return new TipoAtendimentoListDto(
                tipoAtendimento.getId(),
                tipoAtendimento.getNome(),
                tipoAtendimento.getDescricao(),
                tipoAtendimento.getDtCriacao()
        );
    }


    public static TipoAtendimento toEntity(TipoAtendimentoRequestDto tipoAtendimentoRequestDto) {
        return new TipoAtendimento(
                null,
                tipoAtendimentoRequestDto.getNome(),
                tipoAtendimentoRequestDto.getDescricao(),
                tipoAtendimentoRequestDto.getDtCriacao()
        );
    }

    public static TipoAtendimento toEntityUpdate(TipoAtendimento tipoAtendimento, TipoAtendimentoAtualizarDto tipoAtendimentoAtualizarDto) {
        tipoAtendimento.setNome(tipoAtendimentoAtualizarDto.getNome());
        tipoAtendimento.setDescricao(tipoAtendimentoAtualizarDto.getDescricao());
        return tipoAtendimento;
    }


}

