package school.sptech.refuge.core.application.usecase.endereco;

import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.application.exception.EnderecoNaoEncontradoException;
import school.sptech.refuge.core.domain.endereco.Endereco;

public class AtualizarEnderecoUseCase {

    private final EnderecoGateway enderecoRepository;

    public AtualizarEnderecoUseCase(EnderecoGateway enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoResponseDto executar(Integer id, EnderecoRequestDto dto) {
        if (!enderecoRepository.existsById(id)) {
            throw new EnderecoNaoEncontradoException("Endereço de id " + id + " não encontrado");
        }
        Endereco endereco = new Endereco(
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
        Endereco atualizado = enderecoRepository.save(endereco);
        return new EnderecoResponseDto(
                atualizado.getId(),
                atualizado.getTipoLogradouro(),
                atualizado.getNomeLogradouro(),
                atualizado.getNumero(),
                atualizado.getComplemento(),
                atualizado.getBairro(),
                atualizado.getCep(),
                atualizado.getNomeLocalidade(),
                atualizado.getSiglaCidade()
        );
    }
}
