package school.sptech.refuge.core.application.dtos.tipoAtendimento;

import school.sptech.refuge.core.application.dtos.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.domain.funcionario.valueobject.Funcionario;
import school.sptech.refuge.core.domain.tipoatendimento.valueobject.TipoAtendimento;

import java.util.List;

public class TipoAtendimentoMapper {
    public static TipoAtendimento toEntity(TipoAtendimentoRequestDto dto){
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        TipoAtendimento entity = new TipoAtendimento(
                null,
                dto.getNome(),
                dto.getDescricao(),
                dto.getDtCriacao(),
                funcionario
        );

        return entity;
    }

    public static TipoAtendimento toEntity(TipoAtendimentoRequestDto dto, Integer id){
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        TipoAtendimento entity = new TipoAtendimento(
                id,
                dto.getNome(),
                dto.getDescricao(),
                dto.getDtCriacao(),
                funcionario
        );

        return entity;
    }

    /*public TipoAtendimentoResponseDto toDto(TipoAtendimento entity){
        TipoAtendimentoResponseDto dto = new TipoAtendimentoResponseDto();

        dto.setId(entity.getId_TipoAtendimento());
        dto.setNome(entity.getNome());

        return dto;
    }*/

    public static TipoAtendimentoResponseDto toListagemDto(TipoAtendimento entity) {

        if (entity == null) {
            return null;
        }

        FuncionarioListDto funcionarioDto = new FuncionarioListDto(
                entity.getFuncionario().getId(),
                entity.getFuncionario().getNome(),
                entity.getFuncionario().getCpf(),
                entity.getFuncionario().getEmail(),
                entity.getFuncionario().getTelefone()
        );

        return new TipoAtendimentoResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getDataCriacao(),
                funcionarioDto
        );
    }


    public static List<TipoAtendimentoResponseDto> toListagemDtos(List<TipoAtendimento> atendimentos){
        if (atendimentos == null) {
            return null;
        }

        return atendimentos.stream()
                .map(TipoAtendimentoMapper::toListagemDto)
                .toList();
    }
}
