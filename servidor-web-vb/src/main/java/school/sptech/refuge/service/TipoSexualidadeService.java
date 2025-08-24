package school.sptech.refuge.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.domain.tiposexualidade.valueobject.TipoSexualidade;
import school.sptech.refuge.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.exception.ViolacaoDeDadosException;
import school.sptech.refuge.infrastructure.repository.TipoSexualidadeRepository;

import java.util.List;

@Service
public class TipoSexualidadeService {

    private final TipoSexualidadeRepository tipoSexualidadeRepository;

    public TipoSexualidadeService(TipoSexualidadeRepository tipoSexualidadeRepository) {
        this.tipoSexualidadeRepository = tipoSexualidadeRepository;
    }

    public TipoSexualidade cadastrar(TipoSexualidade tipoSexualidade) {

        return tipoSexualidadeRepository.save(tipoSexualidade);
    }

    public TipoSexualidade buscarPorId(Integer id) {
        return tipoSexualidadeRepository.findById(id)
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade de id %d não encontrado".formatted(id)));
    }

    public List<TipoSexualidade> listar() {

        return tipoSexualidadeRepository.findAll();
    }

    public List<TipoSexualidade> buscarPorDescricao(String descricao) {
        return tipoSexualidadeRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public TipoSexualidade atualizar(TipoSexualidade tipoSexualidade) {
        if (tipoSexualidadeRepository.existsById(tipoSexualidade.getId())) {
            tipoSexualidade.setId(tipoSexualidade.getId());
            return tipoSexualidadeRepository.save(tipoSexualidade);
        } else {
            throw new TipoSexualidadeNaoEncontradoException("Tipo sexualidade de id %d não encontrado".formatted(tipoSexualidade.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (!tipoSexualidadeRepository.existsById(id)) {
            throw new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade de id %d não encontrado".formatted(id));
        }

        try {
            tipoSexualidadeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o tipo de sexualidade, pois existem registros relacionados a ele.");
        }
    }
}
