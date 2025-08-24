package school.sptech.refuge.core.adapters.dto.funcionario;

import school.sptech.refuge.core.domain.funcionario.valueobject.Funcionario;

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
}
