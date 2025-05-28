package school.sptech.refuge.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.exception.EnderecoNaoEncontradoException;
import school.sptech.refuge.repository.EnderecoRepository;
import school.sptech.refuge.service.EnderecoService;

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