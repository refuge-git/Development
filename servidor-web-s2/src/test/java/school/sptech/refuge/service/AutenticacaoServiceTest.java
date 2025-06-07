package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.repository.FuncionarioRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("Deve retornar UserDetails quando o e-mail for encontrado")
    void deveRetornarUserDetailsQuandoEmailEncontrado() {

        Funcionario funcionario = new Funcionario(1, "Enzo", "12345678900", "11999999999", "enzo@email.com", "senha123");
        when(funcionarioRepository.findByEmail("enzo@email.com"))
                .thenReturn(Optional.of(funcionario));

        UserDetails userDetails = autenticacaoService.loadUserByUsername("enzo@email.com");

        assertNotNull(userDetails);
        assertEquals("enzo@email.com", userDetails.getUsername());
        verify(funcionarioRepository, times(1)).findByEmail("enzo@email.com");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail não for encontrado")
    void deveLancarExcecaoQuandoEmailNaoForEncontrado() {

        when(funcionarioRepository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoService.loadUserByUsername("naoexiste@email.com")
        );

        assertEquals("usuario: naoexiste@email.com nao encontrado", exception.getMessage());
        verify(funcionarioRepository, times(1)).findByEmail("naoexiste@email.com");
    }
}
