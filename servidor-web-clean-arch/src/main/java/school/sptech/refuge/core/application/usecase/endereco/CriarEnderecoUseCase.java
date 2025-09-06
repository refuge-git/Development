package school.sptech.refuge.core.application.usecase.endereco;

import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.domain.endereco.Endereco;

public class CriarEnderecoUseCase {

    private final EnderecoGateway enderecoRepository;

    public CriarEnderecoUseCase(EnderecoGateway enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoResponseDto executar(EnderecoRequestDto dto) {
        Endereco endereco = new Endereco(
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
        Endereco salvo = enderecoRepository.save(endereco);
        return new EnderecoResponseDto(
                salvo.getId(),
                salvo.getTipoLogradouro(),
                salvo.getNomeLogradouro(),
                salvo.getNumero(),
                salvo.getComplemento(),
                salvo.getBairro(),
                salvo.getCep(),
                salvo.getNomeLocalidade(),
                salvo.getSiglaCidade()
        );
    }
}
