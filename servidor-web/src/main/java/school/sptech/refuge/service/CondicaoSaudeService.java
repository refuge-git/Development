package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.CondicaoSaude;
import school.sptech.refuge.repository.CondicaoSaudeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CondicaoSaudeService {

    private final CondicaoSaudeRepository repository;

    public CondicaoSaudeService(CondicaoSaudeRepository repository) {
        this.repository = repository;
    }

    public CondicaoSaude salvar(CondicaoSaude condicao) {
        return repository.save(condicao);
    }

    public List<CondicaoSaude> listar() {
        return repository.findAll();
    }

    public Optional<CondicaoSaude> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<CondicaoSaude> atualizar(Integer id, CondicaoSaude novaCondicao) {
        return repository.findById(id).map(condicao -> {
            condicao.setDescricao(novaCondicao.getDescricao());
            condicao.setDataDiagnostico(novaCondicao.getDataDiagnostico());
            condicao.setTratamento(novaCondicao.getTratamento());
            condicao.setObservacoes(novaCondicao.getObservacoes());
            return repository.save(condicao);
        });
    }

}
