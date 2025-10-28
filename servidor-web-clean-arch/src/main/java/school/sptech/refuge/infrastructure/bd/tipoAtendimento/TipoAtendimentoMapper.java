package school.sptech.refuge.infrastructure.bd.tipoAtendimento;

import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioEntity;
import school.sptech.refuge.infrastructure.bd.tipoAtendimento.TipoAtendimentoEntity;

import java.util.List;
import java.util.Objects;

public class TipoAtendimentoMapper {

    // Converte DTO de request para domínio
    public static TipoAtendimento toEntity(TipoAtendimentoRequestDto dto){
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        return new TipoAtendimento(
                null,
                dto.getNome(),
                dto.getDescricao(),
                dto.getDtCriacao(),
                funcionario
        );
    }

    public static TipoAtendimento toEntity(TipoAtendimentoRequestDto dto, Integer id){
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        return new TipoAtendimento(
                id,
                dto.getNome(),
                dto.getDescricao(),
                dto.getDtCriacao(),
                funcionario
        );
    }

    // Converte domínio para DTO de response (apenas id do funcionário)
    public static TipoAtendimentoResponseDto toListagemDto(TipoAtendimento entity) {
        if (entity == null) {
            return null;
        }

        return new TipoAtendimentoResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getDataCriacao(),
                entity.getFuncionario() != null ? entity.getFuncionario().getId() : null
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

    // Converte domínio para entidade JPA
    public static TipoAtendimentoEntity ofDomain(TipoAtendimento tipoAtendimento) {
        if (Objects.isNull(tipoAtendimento)) {
            return null;
        }
        TipoAtendimentoEntity entity = new TipoAtendimentoEntity();
        entity.setId(tipoAtendimento.getId());
        entity.setNome(tipoAtendimento.getNome());
        entity.setDescricao(tipoAtendimento.getDescricao());
        entity.setDataCriacao(tipoAtendimento.getDataCriacao());

        if (tipoAtendimento.getFuncionario() != null) {
            FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
            funcionarioEntity.setId(tipoAtendimento.getFuncionario().getId());
            entity.setFuncionario(funcionarioEntity);
        }


        return entity;
    }

    // Converte entidade JPA para domínio
    public static TipoAtendimento ofEntity(TipoAtendimentoEntity tipoAtendimentoEntity) {
        if (Objects.isNull(tipoAtendimentoEntity)) {
            return null;
        }
        TipoAtendimento dominio = new TipoAtendimento();
        dominio.setId(tipoAtendimentoEntity.getId());
        dominio.setNome(tipoAtendimentoEntity.getNome());
        dominio.setDescricao(tipoAtendimentoEntity.getDescricao());
        dominio.setDataCriacao(tipoAtendimentoEntity.getDataCriacao());

        if (tipoAtendimentoEntity.getFuncionario() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(tipoAtendimentoEntity.getFuncionario().getId());
            dominio.setFuncionario(funcionario);
        }


        return dominio;
    }

    // Converte domínio para DTO de response (apenas id do funcionário)
    public static TipoAtendimentoResponseDto fromDomain(TipoAtendimento entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return new TipoAtendimentoResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getDataCriacao(),
                entity.getFuncionario() != null ? entity.getFuncionario().getId() : null
        );
    }

    public static TipoAtendimentoResponseDto of(TipoAtendimento tipoAtendimento) {
        if (tipoAtendimento == null) return null;

        TipoAtendimentoResponseDto dto = new TipoAtendimentoResponseDto();
        dto.setId(tipoAtendimento.getId());
        dto.setNome(tipoAtendimento.getNome());
        dto.setDescricao(tipoAtendimento.getDescricao());
        dto.setFuncionario(tipoAtendimento.getFuncionario() != null ? tipoAtendimento.getFuncionario().getId() : null);

        return dto;
    }
}
