package school.sptech.refuge.dto.funcionario;

import school.sptech.refuge.entity.Funcionario;

import java.util.List;

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
}
