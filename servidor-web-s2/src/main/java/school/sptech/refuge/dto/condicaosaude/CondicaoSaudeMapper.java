package school.sptech.refuge.dto.condicaosaude;

import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.categoria.CategoriaListDto;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.funcionario.FuncionarioAtualizacaoDto;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class CondicaoSaudeMapper {
    public static CondicaoSaudeListDto toListagemDto(CondicaoSaude entity) {
        if (entity == null) return null;

        CategoriaListDto categoriaListDto = new CategoriaListDto(
                entity.getCategoria().getId(),
                entity.getCategoria().getNome()
        );

        FuncionarioListDto funcionarioDto = new FuncionarioListDto(
                entity.getBeneficiario().getFuncionario().getId(),
                entity.getBeneficiario().getFuncionario().getNome(),
                entity.getBeneficiario().getFuncionario().getCpf(),
                entity.getBeneficiario().getFuncionario().getEmail(),
                entity.getBeneficiario().getFuncionario().getTelefone()
        );

        EnderecoListDto enderecoListDto = new EnderecoListDto(
                entity.getBeneficiario().getEndereco().getId(),
                entity.getBeneficiario().getEndereco().getTipoLogradouro(),
                entity.getBeneficiario().getEndereco().getNomeLogradouro(),
                entity.getBeneficiario().getEndereco().getNumero(),
                entity.getBeneficiario().getEndereco().getComplemento(),
                entity.getBeneficiario().getEndereco().getBairro(),
                entity.getBeneficiario().getEndereco().getCep(),
                entity.getBeneficiario().getEndereco().getNomeLocalidade(),
                entity.getBeneficiario().getEndereco().getSiglaCidade()

        );

        TipoGeneroListDto tipoGeneroListDto = new TipoGeneroListDto(
                entity.getBeneficiario().getTipoGenero().getId(),
                entity.getBeneficiario().getTipoGenero().getNome(),
                entity.getBeneficiario().getTipoGenero().getDescricao()
        );

        TipoSexualidadeListDto tipoSexualidadeListDto = new TipoSexualidadeListDto(
                entity.getBeneficiario().getTipoSexualidade().getId(),
                entity.getBeneficiario().getTipoSexualidade().getNome(),
                entity.getBeneficiario().getTipoSexualidade().getDescricao()
        );

        String descricaoStatus = entity.getBeneficiario().getStatus() != null ? entity.getBeneficiario().getStatus().getDescricaoStatus() : "Status não definido";

        BeneficarioListDto beneficarioListDto = new BeneficarioListDto(
                entity.getBeneficiario().getId(),
                entity.getBeneficiario().getNomeRegistro(),
                entity.getBeneficiario().getNomeSocial(),
                entity.getBeneficiario().getDtNasc(),
                entity.getBeneficiario().getCpf(),
                entity.getBeneficiario().getEstrangeiro(),
                entity.getBeneficiario().getRaca().getDescricaoRaca(),
                entity.getBeneficiario().getSexo().getDescricaoSexo(),
                entity.getBeneficiario().getNomeMae(),
                entity.getBeneficiario().getEgressoPrisional(),
                entity.getBeneficiario().getLocalDorme().getDescricaoLocal(),
                entity.getBeneficiario().getFotoPerfil(),
                entity.getBeneficiario().getSisa(),
                descricaoStatus,
                entity.getBeneficiario().getDataAtivacao(),
                entity.getBeneficiario().getObservacao(),
                funcionarioDto,
                enderecoListDto,
                tipoGeneroListDto,
                tipoSexualidadeListDto
        );

        return new CondicaoSaudeListDto(
                entity.getId(),
                entity.getDescricao(),
                entity.getDataRegistro(),
                entity.getTratamento(),
                entity.getObservacoes(),
                beneficarioListDto,
                categoriaListDto
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
    public static CondicaoSaude toEntity(CondicaoSaudeRequestDto request) {
        if (request == null) return null;

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(request.getIdBeneficiario());

        Categoria categoria = new Categoria();
        categoria.setId(request.getIdCategoria());

        return new CondicaoSaude(
                null,
                request.getDescricao(),
                null,
                request.getTratamento(),
                request.getObservacoes(),
                beneficiario,
                categoria
        );
    }

    public static CondicaoSaude toEntity(CondicaoSaudeAtualizacaoDto dto, Integer id) {
        if (dto == null) return null;

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(dto.getIdBeneficiario());

        Categoria categoria = new Categoria();
        categoria.setId(dto.getIdCategoria());

        return new CondicaoSaude(
                id,
                dto.getDescricao(),
                null,
                dto.getTratamento(),
                dto.getObservacoes(),
                beneficiario,
                categoria
        );
    }

}
