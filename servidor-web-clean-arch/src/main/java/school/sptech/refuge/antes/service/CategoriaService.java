package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
//import school.sptech.refuge.exception.*;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaEntity cadastrar(CategoriaEntity categoriaEntity) {
        return categoriaRepository.save(categoriaEntity);
    }

    public List<CategoriaEntity> listar() {
        return categoriaRepository.findAll(); }

    public CategoriaEntity atualizar(CategoriaEntity categoriaEntity) {
        if(categoriaRepository.existsById((categoriaEntity.getId()))) {
            return categoriaRepository.save(categoriaEntity);
        } else {
            throw new EntidadeNaoEncontradaException("Categoria com id %d não encontrada".formatted(categoriaEntity.getId()));
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

    public List<CategoriaEntity> buscarPorNome(String nome) {

        return categoriaRepository.findAllByNome(nome);
    }

    public CategoriaEntity buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria de id %d não encontrado".formatted(id)));
    }

}
