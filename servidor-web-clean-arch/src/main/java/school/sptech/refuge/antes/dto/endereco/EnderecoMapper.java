package school.sptech.refuge.antes.dto.endereco;


import school.sptech.refuge.core.domain.endereco.Endereco;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoEntity;


import java.util.List;
import java.util.Objects;

public class EnderecoMapper {

    public static EnderecoEntity ofDomain(Endereco endereco) {
        if (Objects.isNull(endereco)) {
            return null;
        }

        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(endereco.getId());
        entity.setTipoLogradouro(endereco.getTipoLogradouro());
        entity.setNomeLogradouro(endereco.getNomeLogradouro());
        entity.setNumero(endereco.getNumero());
        entity.setComplemento(endereco.getComplemento());
        entity.setBairro(endereco.getBairro());
        entity.setCep(endereco.getCep());
        entity.setNomeLocalidade(endereco.getNomeLocalidade());
        entity.setSiglaCidade(endereco.getSiglaCidade());

        return entity;
    }

    public static Endereco ofEntity(EnderecoEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setId(entity.getId());
        endereco.setTipoLogradouro(entity.getTipoLogradouro());
        endereco.setNomeLogradouro(entity.getNomeLogradouro());
        endereco.setNumero(entity.getNumero());
        endereco.setComplemento(entity.getComplemento());
        endereco.setBairro(entity.getBairro());
        endereco.setCep(entity.getCep());
        endereco.setNomeLocalidade(entity.getNomeLocalidade());
        endereco.setSiglaCidade(entity.getSiglaCidade());

        return endereco;
    }

    public static EnderecoListDto toListagemDto(Endereco entity) {

        if (entity == null) {
            return null;
        }

        return new EnderecoListDto(
                entity.getId(),
                entity.getTipoLogradouro(),
                entity.getNomeLogradouro(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCep(),
                entity.getNomeLocalidade(),
                entity.getSiglaCidade()
        );
    }

    public static List<EnderecoListDto> toListagemDtos(List<Endereco> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(EnderecoMapper::toListagemDto)
                .toList();
    }


    // SOBRE-CARGA DE MÉTODO
    public static Endereco toEntity(EnderecoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                null,
                dto.getTipoLogradouro(),
                dto.getNomeLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getNomeLocalidade(),
                dto.getSiglaCidade()
        );
    }


    public static Endereco toEntity(EnderecoAtualizacaoDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                id,
                dto.getTipoLogradouro(),
                dto.getNomeLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getNomeLocalidade(),
                dto.getSiglaCidade()
        );
    }


}
