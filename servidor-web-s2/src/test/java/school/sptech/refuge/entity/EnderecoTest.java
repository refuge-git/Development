package school.sptech.refuge.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.exception.EnderecoNaoEncontradoException;
import school.sptech.refuge.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.repository.EnderecoRepository;
import school.sptech.refuge.service.EnderecoService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    @DisplayName("Quando acionar listar e não houver informacoes, deve retornar lista vazia")
    void retornarListaVaziaQuandoAcionarListarNaoHavendoNadaParaRetornar(){

        when(enderecoRepository.findAll()).thenReturn(Collections.emptyList());
        List<Endereco> resultado = enderecoService.listar();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Quando o usuario aciona listar bairro com string vazia, deve retornar lista vazia")
    void retornarListaVaziaQuandoUsuarioColocarBairroVazio() {

        String filtroBairro = "";
        when(enderecoRepository.findByBairroContainingIgnoreCase(filtroBairro)).thenReturn(List.of());

        List<Endereco> resultado = enderecoService.listarPorBairro(filtroBairro);

        assertNotNull(resultado, "Não pode ser nulo ou deixar em branco");
        assertTrue(resultado.isEmpty(), "A lista deveria estar vazia pois não há endereços cadastrados");
        verify(enderecoRepository, times(1)).findByBairroContainingIgnoreCase(filtroBairro);
    }

    @Test
    @DisplayName("Quando o usuário aciona listar nome do logradouro com string vazia, deve retornar lista vazia")
    void retornarListaVaziaQuandoUsuarioColocarNomeLogradouroVazio() {

        String filtroRua = "";
        when(enderecoRepository.findByNomeLogradouroContainingIgnoreCase(filtroRua)).thenReturn(List.of());

        List<Endereco> resultado = enderecoService.listarPorNomeLogradouro(filtroRua);

        assertNotNull(resultado, "A lista retornada não pode ser nula");
        assertTrue(resultado.isEmpty(), "A lista deveria estar vazia pois não há ruas correspondentes");
        verify(enderecoRepository, times(1)).findByNomeLogradouroContainingIgnoreCase(filtroRua);
    }

    @Test
    @DisplayName("Quando o usuário aciona listar tipo do logradouro com string vazia, deve retornar lista vazia")
    void retornarListaVaziaQuandoUsuarioColocarTipoLogradouroVazio() {

        String filtroTipoLogradouro = "";
        when(enderecoRepository.findByTipoLogradouroContainingIgnoreCase(filtroTipoLogradouro)).thenReturn(List.of());

        List<Endereco> resultado = enderecoService.listarPorLogradouro(filtroTipoLogradouro);

        assertNotNull(resultado, "A lista retornada não pode ser nula");
        assertTrue(resultado.isEmpty(), "A lista deveria estar vazia pois não há tipos de logradouro correspondentes");
        verify(enderecoRepository, times(1)).findByTipoLogradouroContainingIgnoreCase(filtroTipoLogradouro);
    }

    @Test
    @DisplayName("Quando atualizar for acionado deve atualizar endereco existente com sucesso")
    void atualizarEnderecoExistenteComSucessoTeste(){

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 123,
                "Ap 10 A", "Jardins", "01419-000", "São Paulo", "SP");

        when(enderecoRepository.existsById(endereco.getId())).thenReturn(true);
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        Endereco atualizado = enderecoService.atualizar(endereco);

        assertNotNull(atualizado);
        assertEquals("Rua", atualizado.getTipoLogradouro());
        assertEquals("Alameda Santos", atualizado.getNomeLogradouro());
        assertEquals("01419-000", atualizado.getCep());
    }

    @Test
    @DisplayName("Deve lançar exceção EntidadeNaoEncontradaException ao tentar atualizar endereco inexistente")
    void atualizarEnderecoInexistenteDeveRetornarEntidadeNaoEncontradaExceptionTeste(){
        Endereco endereco = new Endereco(42, "Rua", "Teste da Silva", 321,
                "Ap 1000", "Testes", "00000-000", "São Paulo", "SP");

        when(enderecoRepository.existsById(endereco.getId())).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> enderecoService.atualizar(endereco));
    }

    @Test
    @DisplayName("Quando remover for acionado deve remover endereco existente com sucesso")
    void removerEnderecoExistenteDeveRemoverSemProblemas(){

        when(enderecoRepository.existsById(1)).thenReturn(true);

        enderecoService.removerPorId(1);

        verify(enderecoRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar execeção EntidadeNaoEncontradaException ao tentar remover endereco inexistente")
    void removerEnderecoInexistenteDeveLancarExcecaoEntidadeNaoEncontradaExceptionTeste(){

        when(enderecoRepository.existsById(99)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> enderecoService.removerPorId(99));
    }

    @Test
    @DisplayName("Retornar um erro quando o CEP for inexistente")
    void retornarMensagemErroQuandoCepNaoExiste(){

        String cepInexistente = "00000-000";
        when(enderecoRepository.findByCep(cepInexistente)).thenReturn(Optional.empty());

        EnderecoNaoEncontradoException exception = assertThrows(
                EnderecoNaoEncontradoException.class, () ->{
                    enderecoService.buscarPorCep((cepInexistente));
                });

        assertEquals("CEP não encontrado", exception.getMessage());
        verify(enderecoRepository, times(1)).findByCep(cepInexistente);

    }


}