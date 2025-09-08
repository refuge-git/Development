package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
//import school.sptech.refuge.exception.*;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaJpaRepository categoriaJpaRepository;

    public CategoriaService(CategoriaJpaRepository categoriaJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
    }

    public CategoriaEntity cadastrar(CategoriaEntity categoriaEntity) {
        return categoriaJpaRepository.save(categoriaEntity);
    }

    public List<CategoriaEntity> listar() {
        return categoriaJpaRepository.findAll(); }

    public CategoriaEntity atualizar(CategoriaEntity categoriaEntity) {
        if(categoriaJpaRepository.existsById((categoriaEntity.getId()))) {
            return categoriaJpaRepository.save(categoriaEntity);
        } else {
            throw new EntidadeNaoEncontradaException("Categoria com id %d não encontrada".formatted(categoriaEntity.getId()));
        }
    }

    public void remover(Integer id) {
        if (!categoriaJpaRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException("Categoria de id %d não encontrado".formatted(id));
        }

        try {
            categoriaJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir a categoria, pois existem registros relacionados a ele.");
        }
    }

    public List<CategoriaEntity> buscarPorNome(String nome) {

        return categoriaJpaRepository.findAllByNome(nome);
    }

    public CategoriaEntity buscarPorId(Integer id) {
        return categoriaJpaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria de id %d não encontrado".formatted(id)));
    }

}
