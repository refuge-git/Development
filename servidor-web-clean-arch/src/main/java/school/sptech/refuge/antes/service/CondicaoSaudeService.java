package school.sptech.refuge.antes.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.core.application.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.core.application.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaEntity;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeEntity;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaRepository;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeRepository;

import java.util.List;

@Service
public class CondicaoSaudeService {
    private final CondicaoSaudeRepository condicaoSaudeRepository;
    private final CategoriaRepository categoriaRepository;
    private final BeneficiarioJpaRepository beneficiarioJpaRepository;

    public CondicaoSaudeService(CondicaoSaudeRepository condicaoSaudeRepository, CategoriaRepository categoriaRepository, BeneficiarioJpaRepository beneficiarioJpaRepository) {
        this.condicaoSaudeRepository = condicaoSaudeRepository;
        this.categoriaRepository = categoriaRepository;
        this.beneficiarioJpaRepository = beneficiarioJpaRepository;
    }

    public CondicaoSaudeEntity cadastrar(CondicaoSaudeEntity condicaoSaudeEntity) {
        CategoriaEntity categoriaEntity = validarCategoria(condicaoSaudeEntity.getCategoria().getId());
        BeneficiarioEntity beneficiarioEntity = validarBeneficiario(condicaoSaudeEntity.getBeneficiario().getId());
        condicaoSaudeEntity.setCategoria(categoriaEntity);
        condicaoSaudeEntity.setBeneficiario(beneficiarioEntity);
        return condicaoSaudeRepository.save(condicaoSaudeEntity);
    }

    public CategoriaEntity validarCategoria(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria da condição não encontrada"));
    }

    public BeneficiarioEntity validarBeneficiario(Integer id) {
        return beneficiarioJpaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Beneficiário da condição não encontrada"));
    }

    public List<CondicaoSaudeEntity> listar() {
        return condicaoSaudeRepository.findAll();
    }

    public CondicaoSaudeEntity atualizar(CondicaoSaudeEntity condicaoSaudeEntity) {
        if(condicaoSaudeRepository.existsById(condicaoSaudeEntity.getId())) {
            condicaoSaudeEntity.setId(condicaoSaudeEntity.getId());
            return condicaoSaudeRepository.save(condicaoSaudeEntity);
        } else {
            throw new EntidadeNaoEncontradaException("Condição de saude com id %d não encontrada".formatted(condicaoSaudeEntity.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if(condicaoSaudeRepository.existsById(id)) {
            condicaoSaudeRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Condição de saude com id %d não encontrada".formatted(id));
        }
    }

    public List<CondicaoSaudeEntity> listarPorDescricao(String descricao) {
        return condicaoSaudeRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    /*public List<CondicaoSaude> listarPorDataRegistro(LocalDate data) {
        return condicaoSaudeRepository.findAllByDataRegistro(data);
    }*/

    public CondicaoSaudeEntity buscarPorId(Integer id) {
        return condicaoSaudeRepository.findById(id)
                .orElseThrow(() -> new CondicaoSaudeNaoEncontradaException("Condição de saúde de id %d não encontrado".formatted(id)));
    }

    public List<CondicaoSaudeEntity> listarPorIdBeneficiario(Integer idBeneficiario) {
        return condicaoSaudeRepository.findByBeneficiarioId(idBeneficiario);
    }
}
