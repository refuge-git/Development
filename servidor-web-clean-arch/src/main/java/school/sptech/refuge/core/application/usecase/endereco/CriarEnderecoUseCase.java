package school.sptech.refuge.core.application.usecase.endereco;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.domain.endereco.Endereco;

import java.util.Optional;

public class CriarEnderecoUseCase {

    private final EnderecoGateway enderecoRepository;
    private final BeneficiarioGateway beneficiarioGateway;

    public CriarEnderecoUseCase(EnderecoGateway enderecoRepository, BeneficiarioGateway beneficiarioGateway) {
        this.enderecoRepository = enderecoRepository;
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public EnderecoResponseDto executar(EnderecoRequestDto dto) {

        Optional<Endereco> enderecoExistente = enderecoRepository.findByCepAndNumero(dto.getCep(), dto.getNumero());

        Endereco salvo;

        if (enderecoExistente.isPresent()) {
            salvo = enderecoExistente.get();
        } else {
            Endereco endereco = new Endereco(
                    null,
                    dto.getTipoLogradouro(),
                    dto.getNomeLogradouro(),
                    dto.getNumero(),
                    dto.getComplemento(),
                    dto.getBairro(),
                    dto.getCep(),
                    dto.getNomeLocalidade()
            );
            salvo = enderecoRepository.save(endereco);
        }

        beneficiarioGateway.linkEndereco(dto.getIdBeneficiario(), salvo.getId());

        return new EnderecoResponseDto(
                salvo.getId(),
                salvo.getTipoLogradouro(),
                salvo.getNomeLogradouro(),
                salvo.getNumero(),
                salvo.getComplemento(),
                salvo.getBairro(),
                salvo.getCep(),
                salvo.getNomeLocalidade()
        );
    }
}
