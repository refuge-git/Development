package school.sptech.refuge.core.adapters;

import org.springframework.security.core.userdetails.UserDetails;

public interface AutenticacaoGateway {
    UserDetails autenticar(String email, String senha); // delega a autenticação
    String gerarToken(UserDetails userDetails);
}
