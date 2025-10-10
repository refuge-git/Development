package school.sptech.refuge.core.application.usecase.endereco;

import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.exception.EnderecoNaoEncontradoException;

public class DeletarEnderecoUseCase {

    private final EnderecoGateway enderecoRepository;

    public DeletarEnderecoUseCase(EnderecoGateway enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void executar(Integer id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EnderecoNaoEncontradoException("Endereço de id " + id + " não encontrado");
        }
        enderecoRepository.deleteById(id);
    }
}
