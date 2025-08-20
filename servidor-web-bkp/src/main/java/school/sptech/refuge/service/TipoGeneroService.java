package school.sptech.refuge.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.entity.TipoGenero;
import school.sptech.refuge.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.exception.ViolacaoDeDadosException;
import school.sptech.refuge.repository.TipoGeneroRepository;

import java.util.List;

@Service
public class TipoGeneroService {

    private final TipoGeneroRepository tipoGeneroRepository;

    public TipoGeneroService(TipoGeneroRepository tipoGeneroRepository) {
        this.tipoGeneroRepository = tipoGeneroRepository;
    }

    public TipoGenero cadastrar(TipoGenero tipoGenero) {

        return tipoGeneroRepository.save(tipoGenero);
    }

    public TipoGenero buscarPorId(Integer id) {
        return tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de genero de id %d não encontrado".formatted(id)));
    }

    public List<TipoGenero> listar() {

        return tipoGeneroRepository.findAll();
    }

    public List<TipoGenero> buscarPorDescricao(String descricao) {
        return tipoGeneroRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public TipoGenero atualizar(TipoGenero tipoGenero) {
        if (tipoGeneroRepository.existsById(tipoGenero.getId())) {
            tipoGenero.setId(tipoGenero.getId());
            return tipoGeneroRepository.save(tipoGenero);
        } else {
            // REALMENTE É DE BENEFICIÁRIO?
            throw new EntidadeNaoEncontradaException("Tipo genero de id %d não encontrado".formatted(tipoGenero.getId() /*beneficiario.getId()*/));
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
