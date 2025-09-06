package school.sptech.refuge.infrastructure.bd.endereco;

import school.sptech.refuge.core.domain.endereco.Endereco;

public class EnderecoMapper {
    public static EnderecoEntity toEntity(Endereco endereco) {
        if (endereco == null) return null;
        return new EnderecoEntity(
                endereco.getId(),
                endereco.getTipoLogradouro(),
                endereco.getNomeLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNomeLocalidade(),
                endereco.getSiglaCidade()
        );
    }

    public static Endereco toDomain(EnderecoEntity entity) {
        if (entity == null) return null;
        return new Endereco(
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
}
