package school.sptech.refuge.infrastructure.bd.funcionario;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school.sptech.refuge.core.adapters.AutenticacaoGateway;
import school.sptech.refuge.infrastructure.config.AutenticacaoService;
import school.sptech.refuge.infrastructure.config.GerenciadorTokenJwt;

@Component
public class AutenticacaoGatewayImpl implements AutenticacaoGateway {

    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public AutenticacaoGatewayImpl(AuthenticationManager authenticationManager, GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    @Override
    public UserDetails autenticar(String email, String senha) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, senha);

        Authentication auth = authenticationManager.authenticate(authToken);
        return (UserDetails) auth.getPrincipal();
    }

    @Override
    public String gerarToken(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return gerenciadorTokenJwt.generateToken(authToken);
    }
}
