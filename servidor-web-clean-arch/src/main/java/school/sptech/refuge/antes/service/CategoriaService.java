package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.antes.entity.Categoria;
import school.sptech.refuge.antes.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.antes.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria cadastrar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll(); }

    public Categoria atualizar(Categoria categoria) {
        if(categoriaRepository.existsById((categoria.getId()))) {
            return categoriaRepository.save(categoria);
        } else {
            throw new EntidadeNaoEncontradaException("Categoria com id %d não encontrada".formatted(categoria.getId()));
        }
    }

    public void remover(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException("Categoria de id %d não encontrado".formatted(id));
        }

        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir a categoria, pois existem registros relacionados a ele.");
        }
    }

    public List<Categoria> buscarPorNome(String nome) {

        return categoriaRepository.findAllByNome(nome);
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria de id %d não encontrado".formatted(id)));
    }

}
