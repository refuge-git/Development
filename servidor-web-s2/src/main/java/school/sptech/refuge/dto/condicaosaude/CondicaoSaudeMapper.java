package school.sptech.refuge.dto.condicaosaude;

import school.sptech.refuge.dto.funcionario.FuncionarioAtualizacaoDto;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.CondicaoSaude;
import school.sptech.refuge.entity.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

public class CondicaoSaudeMapper {
    public static CondicaoSaudeListDto toListagemDto(CondicaoSaude entity) {
        if (entity == null) return null;

        return new CondicaoSaudeListDto(
                entity.getId(),
                entity.getDescricao(),
                entity.getDataRegistro(),
                entity.getCategoria(),
                entity.getTratamento(),
                entity.getObservacoes()

        );
    }

    // Converte uma lista de entidades CondicaoSaude em uma lista de DTOs
    public static List<CondicaoSaudeListDto> toListagemDtos(List<CondicaoSaude> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(CondicaoSaudeMapper::toListagemDto)
                .toList();
    }

    /* Converte um objeto que veio da requisição (CondicaoSaudeRequestDto) em um objeto de entidade (CondicaoSaudez) que pode ser salvo no banco. */
    public static CondicaoSaude toEntity(CondicaoSaudeRequestDto request, Beneficiario beneficiario, Categoria categoria) {
        if (request == null) return null;
        /* this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
        this.idFuncionario = idFuncionario;
        this.idCategoria = idCategoria;*/

        return new CondicaoSaude(
                null,
                request.getDescricao(),
                request.getDataRegistro(),
                request.getTratamento(),
                request.getObservacoes(),
                beneficiario,
                categoria
        );
    }

    public static CondicaoSaude toEntity(CondicaoSaudeAtualizacaoDto dto, Integer id, Categoria categoria) {
        if (dto == null) return null;

        return new CondicaoSaude(
                id,
                dto.getDescricao(),
                null,
                dto.getTratamento(),
                dto.getObservacoes(),
                null,
                categoria
        );
    }

}
