package school.sptech.refuge.infrastructure.bd.categoria;

import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.domain.categoria.Categoria;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public Optional<Categoria> buscarPorId(Integer id) {
        return CategoriaJpaRepository.findById(id)
                .map(CategoriaMapper::ofEntity);
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

    @Override
    public boolean existePorId(Integer id) {
        return categoriaJpaRepository.existsById(id);
    }
}