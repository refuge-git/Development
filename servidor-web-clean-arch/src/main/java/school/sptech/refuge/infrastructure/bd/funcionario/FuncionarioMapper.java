package school.sptech.refuge.infrastructure.bd.funcionario;

import school.sptech.refuge.core.application.dto.funcionario.*;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

import java.util.List;
import java.util.Objects;

public class FuncionarioMapper {
    public static FuncionarioListDto toListagemDto(Funcionario entity) {

        if (entity == null) {
            return null;
        }

        return new FuncionarioListDto(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getTelefone(),
                entity.getEmail()
        );
    }

    // Converte uma lista de entidades Curso em uma lista de DTOs
    public static List<FuncionarioListDto> toListagemDtos(List<Funcionario> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(FuncionarioMapper::toListagemDto)
                .toList();
    }


    /*Converte um objeto que veio da requisição (CursoRequestDto) em um objeto de entidade (Curso) que pode ser salvo no banco.*/
    public static Funcionario toEntity(FuncionarioRequestDto request){
        if (request == null) {
            return null;
        }

        return new Funcionario(
                null,
                request.getNome(),
                request.getCpf(),
                request.getTelefone(),
                request.getEmail(),
                request.getSenha()
        );
    }

    //Converte um DTO de atualização em uma entidade Curso, mantendo o id original.
    public static Funcionario toEntity(FuncionarioAtualizacaoDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new Funcionario(
                id,
                dto.getNome(),
                dto.getCpf(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getSenha()
        );
    }

    public static Funcionario of(FuncionarioRequestDto funcionarioRequestDto) {
        Funcionario funcionario = new Funcionario();

        funcionario.setEmail(funcionarioRequestDto.getEmail());
        funcionario.setCpf(funcionarioRequestDto.getCpf());
        funcionario.setNome(funcionarioRequestDto.getNome());
        funcionario.setTelefone(funcionarioRequestDto.getTelefone());
        funcionario.setSenha(funcionarioRequestDto.getSenha());

        return funcionario;
    }

    public static Funcionario of(FuncionarioLoginDto funcionarioLoginDto) {
        Funcionario funcionario = new Funcionario();

        funcionario.setEmail(funcionarioLoginDto.getEmail());
        funcionario.setSenha(funcionarioLoginDto.getSenha());

        return funcionario;
    }

    public static FuncionarioTokenDto of(Funcionario funcionario, String token) {
        FuncionarioTokenDto funcionarioTokenDto = new FuncionarioTokenDto();

        funcionarioTokenDto.setUserId(funcionario.getId());
        funcionarioTokenDto.setEmail(funcionario.getEmail());
        funcionarioTokenDto.setNome(funcionario.getNome());
        funcionarioTokenDto.setToken(token);

        return funcionarioTokenDto;
    }

    public static FuncionarioListDto of(Funcionario funcionario) {
        FuncionarioListDto funcionarioListDto = new FuncionarioListDto();

        funcionarioListDto.setId(funcionario.getId());
        funcionarioListDto.setCpf(funcionario.getCpf());
        funcionarioListDto.setTelefone(funcionario.getTelefone());
        funcionarioListDto.setEmail(funcionario.getEmail());
        funcionarioListDto.setNome(funcionario.getNome());

        return funcionarioListDto;
    }

    public static FuncionarioEntity ofDomain(Funcionario funcionario) {
        if (Objects.isNull(funcionario)) {
            return null;
        }
        FuncionarioEntity entity = new FuncionarioEntity();
        entity.setId(funcionario.getId());
        entity.setNome(funcionario.getNome());
        entity.setCpf(funcionario.getCpf());
        entity.setTelefone(funcionario.getTelefone());
        entity.setEmail(funcionario.getEmail());
        entity.setSenha(funcionario.getSenha());

        return entity;
    }

    public static Funcionario ofEntity(FuncionarioEntity funcionarioEntity){
        if (Objects.isNull(funcionarioEntity)) {
            return null;
        }
        Funcionario dominio = new Funcionario();
        dominio.setId(funcionarioEntity.getId());
        dominio.setNome(funcionarioEntity.getNome());
        dominio.setCpf(funcionarioEntity.getCpf());
        dominio.setTelefone(funcionarioEntity.getTelefone());
        dominio.setEmail(funcionarioEntity.getEmail());
        dominio.setSenha(funcionarioEntity.getSenha());
        return dominio;
    }
}
