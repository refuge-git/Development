package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.RegistroAtendimento;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.exception.*;
import school.sptech.refuge.repository.BeneficiarioRepository;
import school.sptech.refuge.repository.RegistroAtendimentoRepository;
import school.sptech.refuge.repository.TipoAtendimentoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistroAtendimentoTest {

    @Mock
    private RegistroAtendimentoRepository registroRepo;
    @Mock
    private TipoAtendimentoRepository tipoRepo;
    @Mock
    private BeneficiarioRepository beneficiarioRepo;
    @InjectMocks
    private RegistroAtendimentoService service;

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

        when(tipoRepo.findById(1)).thenReturn(Optional.of(tipo));
        when(beneficiarioRepo.findById(1)).thenReturn(Optional.of(ben));
        when(registroRepo.save(any())).thenReturn(reg);

        RegistroAtendimento resultado = service.criar(reg);

        assertEquals(reg, resultado);
        verify(registroRepo, times(1)).save(reg);
    }

    @Test
    @DisplayName("Quando acionado deve retornar lista de registros com tamanho 2")
    void deveListarTodosRegistros() {
        List<RegistroAtendimento> lista = List.of(new RegistroAtendimento(), new RegistroAtendimento());
        when(registroRepo.findAll()).thenReturn(lista);

        List<RegistroAtendimento> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Quando acionado deve buscar um registro de atendimento pelo id especificado")
    void deveBuscarPorIdExistente() {
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setId(1);
        when(registroRepo.findById(1)).thenReturn(Optional.of(reg));

        RegistroAtendimento resultado = service.buscarPorId(1);

        assertEquals(reg, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar a exception atendimento não encontrado")
    void deveLancarExcecaoAoBuscarIdInexistente() {
        when(registroRepo.findById(999)).thenReturn(Optional.empty());

        assertThrows(RegistroAtendimentoNaoEncontradoException.class, () -> service.buscarPorId(999));
    }

    @Test
    @DisplayName("Quando acionado deve atualizar um registro de atendimento com dados válidos")
    void deveAtualizarRegistroExistente() {
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setId(1);
        when(registroRepo.existsById(1)).thenReturn(true);
        when(registroRepo.save(reg)).thenReturn(reg);

        RegistroAtendimento atualizado = service.atualizar(reg);

        assertEquals(reg, atualizado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar a exception atendimento inexistente")
    void deveLancarExcecaoAoAtualizarRegistroInexistente() {
        RegistroAtendimento reg = new RegistroAtendimento();
        reg.setId(999);
        when(registroRepo.existsById(999)).thenReturn(false);

        assertThrows(RegistroAtendimentoNaoEncontradoException.class, () -> service.atualizar(reg));
    }

    @Test
    @DisplayName("Quando acionado deve deletar um registro de atendimento e não retornar")
    void deveDeletarRegistroComSucesso() {
        when(registroRepo.existsById(1)).thenReturn(true);

        service.deletar(1);

        verify(registroRepo, times(1)).deleteById(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarRegistroInexistente() {
        when(registroRepo.existsById(999)).thenReturn(false);

        assertThrows(RegistroAtendimentoNaoEncontradoException.class, () -> service.deletar(999));
    }

    @Test
    @DisplayName("Quando acionado deve validar se há o registro de atendimento")
    void deveValidarTipoAtendimentoExistente() {
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setId(1);
        when(tipoRepo.findById(1)).thenReturn(Optional.of(tipo));

        TipoAtendimento resultado = service.validarTipoAtendimento(1);

        assertEquals(tipo, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar a exception TipoAtendimentoInexistente")
    void deveLancarExcecaoAoValidarTipoAtendimentoInexistente() {
        when(tipoRepo.findById(999)).thenReturn(Optional.empty());

        assertThrows(TipoAtendimentoNaoEncotradoException.class, () -> service.validarTipoAtendimento(999));
    }

    @Test
    @DisplayName("Quando acionado deve validar a existência de beneficiário pelo id especificado")
    void deveValidarBeneficiarioExistente() {
        Beneficiario ben = new Beneficiario();
        ben.setId(1);
        when(beneficiarioRepo.findById(1)).thenReturn(Optional.of(ben));

        Beneficiario resultado = service.validarBeneficiario(1);

        assertEquals(ben, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar exception BeneficiarioInexistente")
    void deveLancarExcecaoAoValidarBeneficiarioInexistente() {
        when(beneficiarioRepo.findById(999)).thenReturn(Optional.empty());

        assertThrows(BeneficiarioNaoEncontradaException.class, () -> service.validarBeneficiario(999));
    }
}
