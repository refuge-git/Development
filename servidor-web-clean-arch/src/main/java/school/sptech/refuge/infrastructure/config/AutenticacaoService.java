package school.sptech.refuge.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioDetalhesDto;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioEntity;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioMapper;

import java.util.Objects;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private FuncionarioJpaRepository funcionarioJpaRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<FuncionarioEntity> funcionarioOptional = funcionarioJpaRepository.findByEmail(username);

        if (funcionarioOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));

        }
        return new FuncionarioDetalhesDto(Objects.requireNonNull(FuncionarioMapper.ofEntity(funcionarioOptional.get())));
    }
}
