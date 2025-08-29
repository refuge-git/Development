package school.sptech.refuge.antes.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.antes.exception.CondicaoSaudeNaoEncontradaException;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.antes.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.antes.entity.Categoria;
import school.sptech.refuge.antes.entity.CondicaoSaude;
import school.sptech.refuge.antes.repository.BeneficiarioRepository;
import school.sptech.refuge.antes.repository.CategoriaRepository;
import school.sptech.refuge.antes.repository.CondicaoSaudeRepository;

import java.util.List;

@Service
public class CondicaoSaudeService {
    private final CondicaoSaudeRepository condicaoSaudeRepository;
    private final CategoriaRepository categoriaRepository;
    private final BeneficiarioRepository beneficiarioRepository;

    public CondicaoSaudeService(CondicaoSaudeRepository condicaoSaudeRepository, CategoriaRepository categoriaRepository, BeneficiarioRepository beneficiarioRepository) {
        this.condicaoSaudeRepository = condicaoSaudeRepository;
        this.categoriaRepository = categoriaRepository;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public CondicaoSaude cadastrar(CondicaoSaude condicaoSaude) {
        Categoria categoria = validarCategoria(condicaoSaude.getCategoria().getId());
        BeneficiarioEntity beneficiarioEntity = validarBeneficiario(condicaoSaude.getBeneficiario().getId());
        condicaoSaude.setCategoria(categoria);
        condicaoSaude.setBeneficiario(beneficiarioEntity);
        return condicaoSaudeRepository.save(condicaoSaude);
    }

    public Categoria validarCategoria(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria da condição não encontrada"));
    }

    public BeneficiarioEntity validarBeneficiario(Integer id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Beneficiário da condição não encontrada"));
    }

    public List<CondicaoSaude> listar() {
        return condicaoSaudeRepository.findAll();
    }

    public CondicaoSaude atualizar(CondicaoSaude condicaoSaude) {
        if(condicaoSaudeRepository.existsById(condicaoSaude.getId())) {
            condicaoSaude.setId(condicaoSaude.getId());
            return condicaoSaudeRepository.save(condicaoSaude);
        } else {
            throw new EntidadeNaoEncontradaException("Condição de saude com id %d não encontrada".formatted(condicaoSaude.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if(condicaoSaudeRepository.existsById(id)) {
            condicaoSaudeRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Condição de saude com id %d não encontrada".formatted(id));
        }
    }

    public List<CondicaoSaude> listarPorDescricao(String descricao) {
        return condicaoSaudeRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    /*public List<CondicaoSaude> listarPorDataRegistro(LocalDate data) {
        return condicaoSaudeRepository.findAllByDataRegistro(data);
    }*/

    public CondicaoSaude buscarPorId(Integer id) {
        return condicaoSaudeRepository.findById(id)
                .orElseThrow(() -> new CondicaoSaudeNaoEncontradaException("Condição de saúde de id %d não encontrado".formatted(id)));
    }

    public List<CondicaoSaude> listarPorIdBeneficiario(Integer idBeneficiario) {
        return condicaoSaudeRepository.findByBeneficiarioId(idBeneficiario);
    }
}
