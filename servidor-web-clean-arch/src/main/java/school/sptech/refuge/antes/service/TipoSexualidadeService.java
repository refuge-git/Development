package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeEntity;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.antes.repository.TipoSexualidadeRepository;

import java.util.List;

@Service
public class TipoSexualidadeService {

    private final TipoSexualidadeRepository tipoSexualidadeRepository;

    public TipoSexualidadeService(TipoSexualidadeRepository tipoSexualidadeRepository) {
        this.tipoSexualidadeRepository = tipoSexualidadeRepository;
    }

    public TipoSexualidadeEntity cadastrar(TipoSexualidadeEntity tipoSexualidadeEntity) {

        return tipoSexualidadeRepository.save(tipoSexualidadeEntity);
    }

    public TipoSexualidadeEntity buscarPorId(Integer id) {
        return tipoSexualidadeRepository.findById(id)
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade de id %d não encontrado".formatted(id)));
    }

    public List<TipoSexualidadeEntity> listar() {

        return tipoSexualidadeRepository.findAll();
    }

    public List<TipoSexualidadeEntity> buscarPorDescricao(String descricao) {
        return tipoSexualidadeRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public TipoSexualidadeEntity atualizar(TipoSexualidadeEntity tipoSexualidadeEntity) {
        if (tipoSexualidadeRepository.existsById(tipoSexualidadeEntity.getId())) {
            tipoSexualidadeEntity.setId(tipoSexualidadeEntity.getId());
            return tipoSexualidadeRepository.save(tipoSexualidadeEntity);
        } else {
            throw new TipoSexualidadeNaoEncontradoException("Tipo sexualidade de id %d não encontrado".formatted(tipoSexualidadeEntity.getId()));
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
