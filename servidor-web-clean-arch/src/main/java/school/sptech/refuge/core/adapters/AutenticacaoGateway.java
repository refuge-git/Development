package school.sptech.refuge.core.adapters;

import org.springframework.security.core.userdetails.UserDetails;

public interface AutenticacaoGateway {
    UserDetails loadUserByUsername(String username);
    String gerarToken(UserDetails userDetails);
    boolean validarSenha(String senhaBruta, String senhaCriptografada);
}
