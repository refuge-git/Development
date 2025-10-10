package school.sptech.refuge.infrastructure.bd.categoria;

import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.core.domain.categoria.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaJpaAdapter implements CategoriaGateway{

    private final CategoriaJpaRepository categoriaJpaRepository;

    public CategoriaJpaAdapter(CategoriaJpaRepository categoriaJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        CategoriaEntity categoriaEntity = categoriaJpaRepository.save(CategoriaMapper.ofDomain(categoria));
        return CategoriaMapper.ofEntity(categoriaEntity);
    }

    @Override
    public List<Categoria> listarTodos() {
        return categoriaJpaRepository.findAll()
                .stream()
                .map(CategoriaMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorId(Integer id){
        return categoriaJpaRepository.existsById(id);
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaJpaRepository.findById(id)
                .map(CategoriaMapper::ofEntity);
    }

    @Override
    public Categoria atualizar(Integer id, Categoria categoria){
        if(!categoriaJpaRepository.existsById(id)){
            throw new CategoriaNaoEncontradaException("Tipo de Categoria de id: " + id);
        }

        CategoriaEntity entity = CategoriaMapper.ofDomain(categoria);
        entity.setId(id);

        CategoriaEntity atualizado = categoriaJpaRepository.save(entity);
        return CategoriaMapper.ofEntity(atualizado);

    }

    @Override
    public List<Categoria> buscarPorNome(String nome) {
        return List.of();
    }

    @Override
    public void deletar(Integer id){

        if(categoriaJpaRepository.existsById(id)){
            categoriaJpaRepository.deleteById(id);
        }
    }
}