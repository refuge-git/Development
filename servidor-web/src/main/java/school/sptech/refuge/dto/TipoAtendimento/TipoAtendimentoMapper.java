package school.sptech.refuge.dto.TipoAtendimento;

import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.entity.TipoAtendimento;

import java.util.List;

public class TipoAtendimentoMapper {


    public static TipoAtendimentoListDto toListagemDto(TipoAtendimento tipoAtendimento) {
        if (tipoAtendimento == null) {
            return null;
        }

        return new TipoAtendimentoListDto(
                tipoAtendimento.getId(),
                tipoAtendimento.getNome(),
                tipoAtendimento.getDescricao(),
                tipoAtendimento.getDtCriacao(),
                tipoAtendimento.getFuncionario().getNome()
        );
    }

    public static List<TipoAtendimentoListDto> toListagemDto(List<TipoAtendimento> tipos) {
        if (tipos == null) {
            return null;
        }

        return tipos.stream().map(TipoAtendimentoMapper::toListagemDto).toList();
    }

    public static TipoAtendimento toEntity(TipoAtendimentoRequestDto dto, Funcionario funcionario) {
        if (dto == null || funcionario == null) {
            return null;
        }

        return new TipoAtendimento(
                null,
                dto.getNome(),
                dto.getDescricao(),
                dto.getDtCriacao(),
                funcionario
        );
    }

    public static TipoAtendimento toEntity(TipoAtendimentoAtualizarDto dto, Integer id, Funcionario funcionario) {
        if (dto == null) {
            return null;
        }

        return new TipoAtendimento(
                id,
                dto.getNome(),
                dto.getDescricao(),
                funcionario
        );
    }
}

