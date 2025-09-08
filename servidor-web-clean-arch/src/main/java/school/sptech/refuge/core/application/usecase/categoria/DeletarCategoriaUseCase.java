package school.sptech.refuge.core.application.usecase.categoria;

import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.core.adapters.CategoriaGateway;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;

public class DeletarCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public DeletarCategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    public void execute(Integer id){
        if(!categoriaGateway.existePorId(id)){
            throw new CategoriaNaoEncontradaException("Tipo de categoria não encontrada " + id);
        }
        categoriaGateway.deletar(id);
    }

}
