package school.sptech.refuge.core.application.usecase.endereco;

import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosEnderecosUseCase {

    private final EnderecoGateway enderecoRepository;

    public ListarTodosEnderecosUseCase(EnderecoGateway enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoResponseDto> executar() {
        return enderecoRepository.findAll()
                .stream()
                .map(e -> new EnderecoResponseDto(
                        e.getId(),
                        e.getTipoLogradouro(),
                        e.getNomeLogradouro(),
                        e.getNumero(),
                        e.getComplemento(),
                        e.getBairro(),
                        e.getCep(),
                        e.getNomeLocalidade(),
                        e.getSiglaCidade()
                ))
                .collect(Collectors.toList());
    }
}
