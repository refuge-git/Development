package school.sptech.refuge.core.application.usecase.endereco;

import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.application.exception.EnderecoNaoEncontradoException;

public class BuscarEnderecoPorIdUseCase {

    private final EnderecoGateway enderecoRepository;

    public BuscarEnderecoPorIdUseCase(EnderecoGateway enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoResponseDto executar(Integer id) {
        return enderecoRepository.findById(id)
                .map(e -> new EnderecoResponseDto(
                        e.getId(),
                        e.getTipoLogradouro(),
                        e.getNomeLogradouro(),
                        e.getNumero(),
                        e.getComplemento(),
                        e.getBairro(),
                        e.getCep(),
                        e.getNomeLocalidade()
                ))
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço de id " + id + " não encontrado"));
    }
}
