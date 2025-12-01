package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.adapters.TipoAtendimentoGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.RegistroAtendimentoNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoAtendimentoNaoEncotradoException;
import school.sptech.refuge.core.application.usecase.beneficiario.BuscarBeneficiarioUseCase;
import school.sptech.refuge.core.application.usecase.registroAtendimento.*;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;
import school.sptech.refuge.core.domain.tipoAtendimento.TipoAtendimento;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistroAtendimentoTest {

    @Mock
    private RegistroAtendimentoGateway registroAtendimentoGateway;
    @Mock
    private TipoAtendimentoGateway tipoAtendimentoGateway;
    @Mock
    private BeneficiarioGateway beneficiarioGateway;
    @InjectMocks
    private AtualizarRegistroAtendimentoUseCase atualizarRegistroAtendimentoUseCase ;
    @InjectMocks
    private BuscarAtendimentosPorMesUseCase buscarAtendimentosPorMesUseCase;
    @InjectMocks
    private BuscarIndicadoresDashboardUseCase buscarIndicadoresDashboardUseCase;
    @InjectMocks
    private BuscarMesesDisponiveisRelatorioUseCase buscarMesesDisponiveisRelatorioUseCase;
    @InjectMocks
    private BuscarRegistroAtendimentoUseCase buscarRegistroAtendimentoUseCase;
    @InjectMocks
    private BuscarServicosPorSemanaUseCase buscarServicosPorSemanaUseCase;
    @InjectMocks
    private ContarBeneficiariosAtendidosNoMesUseCase contarBeneficiariosAtendidosNoMesUseCase;
    @InjectMocks
    private CriarRegistroAtendimentoUseCase criarRegistroAtendimentoUseCase;
    @InjectMocks
    private DeletarRegistroAtendimentoUseCase deletarRegistroAtendimentoUseCase;
    @InjectMocks
    private GerarRelatorioCompletoUseCase gerarRelatorioCompletoUseCase;
    @InjectMocks
    private ListarTodosRegistroAtendimentoUseCase listarTodosRegistroAtendimentoUseCase;
    @InjectMocks
    private BuscarBeneficiarioUseCase buscarBeneficiarioUseCase;

    @Test
    @DisplayName("Quando acionado deve criar um registro com dados válidos")
    void deveCriarRegistroComSucesso() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(1);
        Beneficiario ben = new Beneficiario();
        ben.setId(1);
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setTipoAtendimento(tipo);
        reg.setBeneficiario(ben);

        when(tipoAtendimentoGateway.buscarPorId(1)).thenReturn(Optional.of(tipo));
        when(beneficiarioGateway.buscarPorId(1)).thenReturn(Optional.of(ben));
        when(registroAtendimentoGateway.salvar(any())).thenReturn(reg);

        RegistroAtendimentoRequestDto dto = new RegistroAtendimentoRequestDto();
        dto.setIdTipoAtendimento(tipo.getId());
        dto.setIdBeneficiario(ben.getId());

        RegistroAtendimentoResponseDto resultado = criarRegistroAtendimentoUseCase.execute(dto);

        assertEquals(reg, resultado);
        verify(registroAtendimentoGateway, times(1)).salvar(reg);
    }

    @Test
    @DisplayName("Quando acionado deve retornar lista de registros com tamanho 2")
    void deveListarTodosRegistros() {
        List<RegistroAtendimento> lista = List.of(new RegistroAtendimento(), new RegistroAtendimento());
        when(registroAtendimentoGateway.listarTodos()).thenReturn(lista);

        List<RegistroAtendimentoResponseDto> resultado = listarTodosRegistroAtendimentoUseCase.execute();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Quando acionado deve buscar um registro de atendimento pelo id especificado")
    void deveBuscarPorIdExistente() {
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setId(1);
        when(registroAtendimentoGateway.buscarPorId(1)).thenReturn(Optional.of(reg));

        RegistroAtendimentoResponseDto resultado = buscarRegistroAtendimentoUseCase.execute(1);

        assertEquals(reg, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar a exception atendimento não encontrado")
    void deveLancarExcecaoAoBuscarIdInexistente() {
        when(registroAtendimentoGateway.buscarPorId(999)).thenReturn(Optional.empty());

        assertThrows(RegistroAtendimentoNaoEncontradoException.class, () -> buscarRegistroAtendimentoUseCase.execute(999));
    }

    @Test
    @DisplayName("Quando acionado deve atualizar um registro de atendimento com dados válidos")
    void deveAtualizarRegistroExistente() {
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setId(1);
        when(registroAtendimentoGateway.existePorId(1)).thenReturn(true);
        when(registroAtendimentoGateway.salvar(reg)).thenReturn(reg);

        RegistroAtendimentoRequestDto dto = new RegistroAtendimentoRequestDto();
        dto.setIdTipoAtendimento(1);

        RegistroAtendimentoResponseDto atualizado = atualizarRegistroAtendimentoUseCase.execute(1, dto);

        assertEquals(reg, atualizado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar a exception atendimento inexistente")
    void deveLancarExcecaoAoAtualizarRegistroInexistente() {
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setId(999);
        when(registroAtendimentoGateway.existePorId(999)).thenReturn(false);
        RegistroAtendimentoRequestDto dto = new RegistroAtendimentoRequestDto();
        dto.setIdTipoAtendimento(reg.getId());

        assertThrows(RegistroAtendimentoNaoEncontradoException.class, () -> atualizarRegistroAtendimentoUseCase.execute(999,dto));
    }

    @Test
    @DisplayName("Quando acionado deve deletar um registro de atendimento e não retornar")
    void deveDeletarRegistroComSucesso() {
        when(registroAtendimentoGateway.existePorId(1)).thenReturn(true);

        deletarRegistroAtendimentoUseCase.execute(1);

        verify(registroAtendimentoGateway, times(1)).deletar(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarRegistroInexistente() {
        when(registroAtendimentoGateway.existePorId(999)).thenReturn(false);

        assertThrows(RegistroAtendimentoNaoEncontradoException.class, () -> deletarRegistroAtendimentoUseCase.execute(999));
    }

//    @Test
//    @DisplayName("Quando acionado deve validar se há o registro de atendimento")
//    void deveValidarTipoAtendimentoExistente() {
//        TipoAtendimento tipo = new TipoAtendimento();
//        tipo.setId(1);
//        when(tipoAtendimentoGateway.buscarPorId(1)).thenReturn(Optional.of(tipo));
//
//        TipoAtendimento resultado = service.validarTipoAtendimento(1);
//
//        assertEquals(tipo, resultado);
//    }
//
//    @Test
//    @DisplayName("Quando acionado deve lançar a exception TipoAtendimentoInexistente")
//    void deveLancarExcecaoAoValidarTipoAtendimentoInexistente() {
//        when(tipoAtendimentoGateway.buscarPorId(999)).thenReturn(Optional.empty());
//
//        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> b.validarTipoAtendimento(999));
//    }

    @Test
    @DisplayName("Quando acionado deve validar a existência de beneficiário pelo id especificado")
    void deveValidarBeneficiarioExistente() {
        Beneficiario ben = new Beneficiario();
        ben.setId(1);
        when(beneficiarioGateway.buscarPorId(1)).thenReturn(Optional.of(ben));

        BeneficarioListDto resultado = buscarBeneficiarioUseCase.execute(1);

        assertEquals(ben, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar exception BeneficiarioInexistente")
    void deveLancarExcecaoAoValidarBeneficiarioInexistente() {
        when(beneficiarioGateway.buscarPorId(999)).thenReturn(Optional.empty());

        assertThrows(BeneficiarioNaoEncontradaException.class, () -> buscarBeneficiarioUseCase.execute(999));
    }
}
