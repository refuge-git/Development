package school.sptech.refuge.infrastructure.bd.funcionario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school.sptech.refuge.core.adapters.AutenticacaoGateway;
import school.sptech.refuge.infrastructure.config.AutenticacaoService;
import school.sptech.refuge.infrastructure.config.GerenciadorTokenJwt;

@Component
public class AutenticacaoGatewayImpl implements AutenticacaoGateway {

    private final AutenticacaoService autenticacaoService;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoGatewayImpl(AutenticacaoService autenticacaoService, GerenciadorTokenJwt gerenciadorTokenJwt, PasswordEncoder passwordEncoder) {
        this.autenticacaoService = autenticacaoService;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return autenticacaoService.loadUserByUsername(username);
    }

    @Override
    public String gerarToken(UserDetails userDetails) {
        // Aqui você pode criar um Authentication fake ou adaptar conforme seu GerenciadorTokenJwt
        return gerenciadorTokenJwt.generateToken(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                )
        );
    }

    @Override
    public boolean validarSenha(String senhaBruta, String senhaCriptografada) {
        return passwordEncoder.matches(senhaBruta, senhaCriptografada);
    }
}
