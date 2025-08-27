package school.sptech.refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.application.dtos.funcionario.FuncionarioDetalhesDto;
import school.sptech.refuge.core.domain.funcionario.valueobject.Funcionario;
import school.sptech.refuge.infrastructure.repository.FuncionarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByEmail(username);

        if (funcionarioOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));

        }
        return new FuncionarioDetalhesDto(funcionarioOptional.get());
    }
}
