package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.application.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.application.exception.EnderecoNaoEncontradoException;
import school.sptech.refuge.core.application.usecase.endereco.*;
import school.sptech.refuge.core.domain.endereco.Endereco;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoTest {

    @InjectMocks
    private AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    @InjectMocks
    private BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase;
    @InjectMocks
    private CriarEnderecoUseCase criarEnderecoUseCase;
    @InjectMocks
    private DeletarEnderecoUseCase deletarEnderecoUseCase;
    @InjectMocks
    private ListarTodosEnderecosUseCase listarTodosEnderecosUseCase;

    @Mock
    private EnderecoGateway enderecoGateway;

    @Test
    @DisplayName("Deve cadastrar endereço com todos os dados preenchidos corretamente")
    void deveCadastrarEnderecoComSucesso() {

        Endereco endereco = new Endereco(1, "Rua",
                "Alameda Santos",
                456,
                "Ap 10 A",
                "Centro",
                "01310-000",
                "São Paulo");

        when(enderecoGateway.save(endereco)).thenReturn(endereco);

//        this.tipoLogradouro = tipoLogradouro;
//        this.nomeLogradouro = nomeLogradouro;
//        this.numero = numero;
//        this.complemento = complemento;
//        this.bairro = bairro;
//        this.cep = cep;
//        this.nomeLocalidade = nomeLocalidade;
//        this.idBeneficiario = idBeneficiario;

        EnderecoRequestDto dto = new EnderecoRequestDto();
        dto.setTipoLogradouro(endereco.getTipoLogradouro());
        dto.setNomeLogradouro(endereco.getNomeLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCep(endereco.getCep());
        dto.setNomeLocalidade(endereco.getNomeLocalidade());
        //dto.setIdBeneficiario(endereco.getIdBeneficiario());

        EnderecoResponseDto resultado = criarEnderecoUseCase.executar(dto);

        assertNotNull(resultado);
        assertEquals("Rua", resultado.getTipoLogradouro());
        assertEquals("Alameda Santos", resultado.getNomeLogradouro());
        assertEquals("Centro", resultado.getBairro());
        verify(enderecoGateway, times(1)).save(endereco);
    }


    @Test
    @DisplayName("Deve retornar endereço existente ao buscar por ID")
    void deveRetornarEnderecoQuandoIdExiste() {

        Integer id = 1;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setTipoLogradouro("Rua");
        endereco.setNomeLogradouro("das Flores");

        when(enderecoGateway.findById(id)).thenReturn(Optional.of(endereco));

        EnderecoResponseDto resultado = buscarEnderecoPorIdUseCase.executar(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Rua", resultado.getTipoLogradouro());
        verify(enderecoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar endereço por ID inexistente")
    void deveLancarExcecaoQuandoIdNaoExiste() {

        Integer idInexistente = 999;
        when(enderecoGateway.findById(idInexistente)).thenReturn(Optional.empty());

        EnderecoNaoEncontradoException excecao = assertThrows(
                EnderecoNaoEncontradoException.class,
                () -> buscarEnderecoPorIdUseCase.executar(idInexistente));

        assertEquals("Endereço de id 999 não encontrado", excecao.getMessage());
        verify(enderecoGateway, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Quando acionar listar e não houver informacoes, deve retornar lista vazia")
    void retornarListaVaziaQuandoAcionarListarNaoHavendoNadaParaRetornar(){

        when(enderecoGateway.findAll()).thenReturn(Collections.emptyList());
        List<EnderecoResponseDto> resultado = listarTodosEnderecosUseCase.executar();
        assertTrue(resultado.isEmpty());
    }

//    @Test
//    @DisplayName("Quando o usuario aciona listar bairro com string vazia, deve retornar lista vazia")
//    void retornarListaVaziaQuandoUsuarioColocarBairroVazio() {
//
//        String filtroBairro = "";
//        when(enderecoGateway.findByBairroContainingIgnoreCase(filtroBairro)).thenReturn(List.of());
//
//        List<Endereco> resultado = enderecoService.listarPorBairro(filtroBairro);
//
//        assertNotNull(resultado, "Não pode ser nulo ou deixar em branco");
//        assertTrue(resultado.isEmpty(), "A lista deveria estar vazia pois não há endereços cadastrados");
//        verify(enderecoGateway, times(1)).findByBairroContainingIgnoreCase(filtroBairro);
//    }
//
//    @Test
//    @DisplayName("Quando o usuário aciona listar nome do logradouro com string vazia, deve retornar lista vazia")
//    void retornarListaVaziaQuandoUsuarioColocarNomeLogradouroVazio() {
//
//        String filtroRua = "";
//        when(enderecoGateway.findByNomeLogradouroContainingIgnoreCase(filtroRua)).thenReturn(List.of());
//
//        List<Endereco> resultado = enderecoService.listarPorNomeLogradouro(filtroRua);
//
//        assertNotNull(resultado, "A lista retornada não pode ser nula");
//        assertTrue(resultado.isEmpty(), "A lista deveria estar vazia pois não há ruas correspondentes");
//        verify(enderecoGateway, times(1)).findByNomeLogradouroContainingIgnoreCase(filtroRua);
//    }
//
//    @Test
//    @DisplayName("Quando o usuário aciona listar tipo do logradouro com string vazia, deve retornar lista vazia")
//    void retornarListaVaziaQuandoUsuarioColocarTipoLogradouroVazio() {
//
//        String filtroTipoLogradouro = "";
//        when(enderecoGateway.findByTipoLogradouroContainingIgnoreCase(filtroTipoLogradouro)).thenReturn(List.of());
//
//        List<Endereco> resultado = enderecoService.listarPorLogradouro(filtroTipoLogradouro);
//
//        assertNotNull(resultado, "A lista retornada não pode ser nula");
//        assertTrue(resultado.isEmpty(), "A lista deveria estar vazia pois não há tipos de logradouro correspondentes");
//        verify(enderecoGateway, times(1)).findByTipoLogradouroContainingIgnoreCase(filtroTipoLogradouro);
//    }

    @Test
    @DisplayName("Quando atualizar for acionado deve atualizar endereco existente com sucesso")
    void atualizarEnderecoExistenteComSucessoTeste(){

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 123,
                "Ap 10 A", "Jardins", "01419-000", "São Paulo");

        when(enderecoGateway.existsById(endereco.getId())).thenReturn(true);
        when(enderecoGateway.save(endereco)).thenReturn(endereco);

        EnderecoRequestDto dto = new EnderecoRequestDto();
        dto.setTipoLogradouro(endereco.getTipoLogradouro());
        dto.setNomeLogradouro(endereco.getNomeLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCep(endereco.getCep());
        dto.setNomeLocalidade(endereco.getNomeLocalidade());

        EnderecoResponseDto atualizado = atualizarEnderecoUseCase.executar(endereco.getId(), dto);

        assertNotNull(atualizado);
        assertEquals("Rua", atualizado.getTipoLogradouro());
        assertEquals("Alameda Santos", atualizado.getNomeLogradouro());
        assertEquals("01419-000", atualizado.getCep());
    }

    @Test
    @DisplayName("Deve lançar exceção EnderecoNaoEncontradoException ao tentar atualizar endereco inexistente")
    void atualizarEnderecoInexistenteDeveRetornarEnderecoNaoEncontradoExceptionTeste(){
        Endereco endereco = new Endereco(42, "Rua", "Teste da Silva", 321,
                "Ap 1000", "Testes", "00000-000", "São Paulo");

        when(enderecoGateway.existsById(endereco.getId())).thenReturn(false);

        EnderecoRequestDto dto = new EnderecoRequestDto();
        dto.setTipoLogradouro(endereco.getTipoLogradouro());
        dto.setNomeLogradouro(endereco.getNomeLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCep(endereco.getCep());
        dto.setNomeLocalidade(endereco.getNomeLocalidade());

        assertThrows(EnderecoNaoEncontradoException.class, () -> atualizarEnderecoUseCase.executar(endereco.getId(), dto));
    }

    @Test
    @DisplayName("Quando remover for acionado deve remover endereco existente com sucesso")
    void removerEnderecoExistenteDeveRemoverSemProblemas(){

        when(enderecoGateway.existsById(1)).thenReturn(true);

        deletarEnderecoUseCase.executar(1);

        verify(enderecoGateway, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar execeção EnderecoNaoEncontradoException ao tentar remover endereco inexistente")
    void removerEnderecoInexistenteDeveLancarExcecaoEnderecoNaoEncontradoExceptionTeste(){

        when(enderecoGateway.existsById(99)).thenReturn(false);

        assertThrows(EnderecoNaoEncontradoException.class, () -> deletarEnderecoUseCase.executar(99));
    }
//
//    @Test
//    @DisplayName("Retornar um erro quando o CEP for inexistente")
//    void retornarMensagemErroQuandoCepNaoExiste(){
//
//        String cepInexistente = "00000-000";
//        when(enderecoGateway.findByCep(cepInexistente)).thenReturn(Optional.empty());
//
//        EnderecoNaoEncontradoException exception = assertThrows(
//                EnderecoNaoEncontradoException.class, () ->{
//                    enderecoService.buscarPorCep((cepInexistente));
//                });
//
//        assertEquals("CEP não encontrado", exception.getMessage());
//        verify(enderecoGateway, times(1)).findByCep(cepInexistente);
//
//    }


}