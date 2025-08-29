package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.antes.repository.TipoGeneroRepository;

import java.util.List;

@Service
public class TipoGeneroService {

    private final TipoGeneroRepository tipoGeneroRepository;

    public TipoGeneroService(TipoGeneroRepository tipoGeneroRepository) {
        this.tipoGeneroRepository = tipoGeneroRepository;
    }

    public TipoGeneroEntity cadastrar(TipoGeneroEntity tipoGeneroEntity) {

        return tipoGeneroRepository.save(tipoGeneroEntity);
    }

    public TipoGeneroEntity buscarPorId(Integer id) {
        return tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de genero de id %d não encontrado".formatted(id)));
    }

    public List<TipoGeneroEntity> listar() {

        return tipoGeneroRepository.findAll();
    }

    public List<TipoGeneroEntity> buscarPorDescricao(String descricao) {
        return tipoGeneroRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public TipoGeneroEntity atualizar(TipoGeneroEntity tipoGeneroEntity) {
        if (tipoGeneroRepository.existsById(tipoGeneroEntity.getId())) {
            tipoGeneroEntity.setId(tipoGeneroEntity.getId());
            return tipoGeneroRepository.save(tipoGeneroEntity);
        } else {
            // REALMENTE É DE BENEFICIÁRIO?
            throw new EntidadeNaoEncontradaException("Tipo genero de id %d não encontrado".formatted(tipoGeneroEntity.getId() /*beneficiario.getId()*/));
        }
    }

    public void removerPorId(Integer id) {
        if (!tipoGeneroRepository.existsById(id)) {
            throw new TipoGeneroNaoEncontradoException("Tipo de gênero de id %d não encontrado".formatted(id));
        }

        try {
            tipoGeneroRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o tipo de gênero, pois existem registros relacionados a ele.");
        }
    }
}
