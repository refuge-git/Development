package school.sptech.refuge.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.exception.CategoriaNaoEncontradaException;
import school.sptech.refuge.repository.CategoriaRepository;
import school.sptech.refuge.service.CategoriaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static school.sptech.refuge.entity.LocalEnum.PENSAO;
import static school.sptech.refuge.entity.RacaEnum.BRANCO;
import static school.sptech.refuge.entity.RacaEnum.PRETO;
import static school.sptech.refuge.entity.SexoEnum.FEMININO;
import static school.sptech.refuge.entity.StatusEnum.ATIVO;
import static school.sptech.refuge.entity.StatusEnum.INATIVO;

@ExtendWith(MockitoExtension.class)
class CategoriaTest {
@InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Cadastrar categoria com dados válidos deve retornar categoria salva")
    void cadastrarCategoriaQuandoForAcionadaDeveRetonarCategoriaCadastradaComSucessoTeste() {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Deficiência");

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria categoriaSalva = categoriaService.cadastrar(categoria);

        assertEquals("Deficiência", categoriaSalva.getNome());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar categoria correspondente")
    void buscarPorIdComIdExistenteDeveRetornarCategoriaTeste(){
        Categoria categoria = new Categoria(2, "Doença");

        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoria));

        Categoria resultado = categoriaService.buscarPorId(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar categoria com ID inexistente")
    void buscarPorIdCategoriaComIdInexistenteDeveLancarExcecaoTeste() {
        when(categoriaRepository.findById(34)).thenReturn(Optional.empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.buscarPorId(34));
    }

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 categorias, deve retornar corretamente")
    void listarQuandoAcionadoComcategoriasCadastradasDeveRetornarCorretamenteTeste(){
        List<Categoria> categorias = List.of(
                new Categoria(1, "Doença"),

                new Categoria(2, "Deficiência"),

                new Categoria(3, "Condição temporaria")
        );

        when(categoriaRepository.findAll()).thenReturn(categorias);
        List<Categoria> listaCategorias = categoriaService.listar();
        assertEquals(3, listaCategorias.size());
    }

    @Test
    @DisplayName("Listar quando acionado e não houver categoria, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDeCategoriaDeveRetornarUmaColecaoVaziaTeste() {

        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());
        List<Categoria> resultado = categoriaService.listar();
        assertTrue(resultado.isEmpty());
    }

}