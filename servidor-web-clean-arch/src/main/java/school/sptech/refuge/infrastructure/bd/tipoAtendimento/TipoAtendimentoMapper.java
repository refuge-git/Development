package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoEntity;

import java.util.List;
import java.util.Objects;

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

    public static TipoAtendimentoEntity ofDomain(TipoAtendimento tipoAtendimento) {
        if (Objects.isNull(tipoAtendimento)) {
            return null;
        }
        TipoAtendimentoEntity entity = new TipoAtendimentoEntity();
        entity.setId(tipoAtendimento.getId());
        entity.setNome(tipoAtendimento.getNome());
        entity.setDescricao(tipoAtendimento.getDescricao());
        entity.setDataCriacao(tipoAtendimento.getDataCriacao());
        return entity;
    }

    public static TipoAtendimento ofEntity(TipoAtendimentoEntity tipoAtendimentoEntity) {
        if (Objects.isNull(tipoAtendimentoEntity)) {
            return null;
        }
        TipoAtendimento dominio = new TipoAtendimento();
        dominio.setId(tipoAtendimentoEntity.getId());
        dominio.setNome(tipoAtendimentoEntity.getNome());
        dominio.setDescricao(tipoAtendimentoEntity.getDescricao());
        dominio.setDataCriacao(tipoAtendimentoEntity.getDataCriacao());
        return dominio;
    }

    public static TipoAtendimentoResponseDto fromDomain(TipoAtendimento entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return new TipoAtendimentoResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getDataCriacao(),
                entity.getFuncionario().getId(),
                entity.getFuncionario().getNome(),
                entity.getFuncionario().getCpf(),
                entity.getFuncionario().getTelefone(),
                entity.getFuncionario().getEmail()
        );
    }
}
