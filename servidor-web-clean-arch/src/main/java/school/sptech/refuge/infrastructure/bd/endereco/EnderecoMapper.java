package school.sptech.refuge.infrastructure.bd.endereco;

import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.domain.endereco.Endereco;
import school.sptech.refuge.core.domain.funcionario.Funcionario;

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
                endereco.getNomeLocalidade()
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
                entity.getNomeLocalidade()
        );
    }

    public static EnderecoEntity ofDomain(Endereco endereco) {
        if (endereco == null) return null;
        return new EnderecoEntity(
                endereco.getId(),
                endereco.getTipoLogradouro(),
                endereco.getNomeLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNomeLocalidade()
        );
    }

    public static Endereco ofEntity(EnderecoEntity entity) {
        if (entity == null) return null;
        return new Endereco(
                entity.getId(),
                entity.getTipoLogradouro(),
                entity.getNomeLogradouro(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCep(),
                entity.getNomeLocalidade()
        );
    }
}
