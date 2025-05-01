package school.sptech.refuge.dto.funcionario;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.refuge.entity.Funcionario;

import java.util.Collection;
import java.util.List;

public class FuncionarioDetalhesDto implements UserDetails {

    @Schema(description = "Nome completo do funcionário", example = "Marcio Santa da Silva")
    private final String nome;
    @Schema(description = "Email do funcionário", example = "marcio@gmail.com")
    private final String email;
    @Schema(description = "Senha de acesso do funcionário", example = "12345678")
    private final String senha;

    public FuncionarioDetalhesDto(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.senha = funcionario.getSenha();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
