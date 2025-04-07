package school.sptech.refuge.service;


import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.Atividade;
import school.sptech.refuge.repository.AtividadeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    private final AtividadeRepository repository;

    public AtividadeService(AtividadeRepository repository) {
        this.repository = repository;
    }

    public Atividade cadastrar(Atividade atividade) {
        return repository.save(atividade);
    }

    public List<Atividade> listar() {
        return repository.findAll();
    }

    public Optional<Atividade> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Atividade> atualizar(Integer id, Atividade novaAtividade) {
        return repository.findById(id).map(existente -> {
            existente.setNome(novaAtividade.getNome());
            existente.setDescricao(novaAtividade.getDescricao());
            existente.setDataHora(novaAtividade.getDataHora());
            return repository.save(existente);
        });
    }

    public boolean deletar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
