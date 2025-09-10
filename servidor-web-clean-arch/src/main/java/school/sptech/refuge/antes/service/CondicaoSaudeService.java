package school.sptech.refuge.antes.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeEntity;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaJpaRepository;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeJpaRepository;

import java.util.List;

@Service
public class CondicaoSaudeService {
    private final CondicaoSaudeJpaRepository condicaoSaudeJpaRepository;
    private final CategoriaJpaRepository categoriaJpaRepository;
    private final BeneficiarioJpaRepository beneficiarioJpaRepository;

    public CondicaoSaudeService(CondicaoSaudeJpaRepository condicaoSaudeJpaRepository, CategoriaJpaRepository categoriaJpaRepository, BeneficiarioJpaRepository beneficiarioJpaRepository) {
        this.condicaoSaudeJpaRepository = condicaoSaudeJpaRepository;
        this.categoriaJpaRepository = categoriaJpaRepository;
        this.beneficiarioJpaRepository = beneficiarioJpaRepository;
    }

    public CondicaoSaudeEntity cadastrar(CondicaoSaudeEntity condicaoSaudeEntity) {
        CategoriaEntity categoriaEntity = validarCategoria(condicaoSaudeEntity.getCategoria().getId());
        BeneficiarioEntity beneficiarioEntity = validarBeneficiario(condicaoSaudeEntity.getBeneficiario().getId());
        condicaoSaudeEntity.setCategoria(categoriaEntity);
        condicaoSaudeEntity.setBeneficiario(beneficiarioEntity);
        return condicaoSaudeJpaRepository.save(condicaoSaudeEntity);
    }

    public CategoriaEntity validarCategoria(Integer id) {
        return categoriaJpaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria da condição não encontrada"));
    }

    public BeneficiarioEntity validarBeneficiario(Integer id) {
        return beneficiarioJpaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Beneficiário da condição não encontrada"));
    }

    public List<CondicaoSaudeEntity> listar() {
        return condicaoSaudeJpaRepository.findAll();
    }

    public CondicaoSaudeEntity atualizar(CondicaoSaudeEntity condicaoSaudeEntity) {
        if(condicaoSaudeJpaRepository.existsById(condicaoSaudeEntity.getId())) {
            condicaoSaudeEntity.setId(condicaoSaudeEntity.getId());
            return condicaoSaudeJpaRepository.save(condicaoSaudeEntity);
        } else {
            throw new EntidadeNaoEncontradaException("Condição de saude com id %d não encontrada".formatted(condicaoSaudeEntity.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if(condicaoSaudeJpaRepository.existsById(id)) {
            condicaoSaudeJpaRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Condição de saude com id %d não encontrada".formatted(id));
        }
    }

    public List<CondicaoSaudeEntity> listarPorDescricao(String descricao) {
        return condicaoSaudeJpaRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    /*public List<CondicaoSaude> listarPorDataRegistro(LocalDate data) {
        return condicaoSaudeRepository.findAllByDataRegistro(data);
    }*/

    public CondicaoSaudeEntity buscarPorId(Integer id) {
        return condicaoSaudeJpaRepository.findById(id)
                .orElseThrow(() -> new CondicaoSaudeNaoEncontradaException("Condição de saúde de id %d não encontrado".formatted(id)));
    }

    public List<CondicaoSaudeEntity> listarPorIdBeneficiario(Integer idBeneficiario) {
        return condicaoSaudeJpaRepository.findByBeneficiarioId(idBeneficiario);
    }
}
