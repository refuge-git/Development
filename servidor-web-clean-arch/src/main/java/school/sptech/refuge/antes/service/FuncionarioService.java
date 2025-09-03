package school.sptech.refuge.antes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.refuge.antes.config.GerenciadorTokenJwt;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioMapper;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioTokenDto;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.antes.exception.EntidadeNaoEncontradaException;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.antes.exception.ViolacaoDeDadosException;
import school.sptech.refuge.antes.repository.FuncionarioRepository;

import java.util.List;

@Service
public class FuncionarioService {

    private final PasswordEncoder passwordEncoder;

    private final FuncionarioRepository funcionarioRepository;

    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    private final AuthenticationManager authenticationManager;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder, GerenciadorTokenJwt gerenciadorTokenJwt, AuthenticationManager authenticationManager) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.authenticationManager = authenticationManager;
    }

    public void criar(Funcionario funcionario) {
        String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        this.funcionarioRepository.save(funcionario);
    }

    public FuncionarioTokenDto autenticar(Funcionario funcionario) {
        final UsernamePasswordAuthenticationToken credetials = new UsernamePasswordAuthenticationToken(funcionario.getEmail(), funcionario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credetials);

        Funcionario funcionarioAutenticado = funcionarioRepository.findByEmail(funcionario.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Emial do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FuncionarioMapper.of(funcionarioAutenticado, token);
    }

    public List<FuncionarioListDto> listarTodos() {
        List<Funcionario> funcionariosEncontrados = funcionarioRepository.findAll();
        return funcionariosEncontrados.stream().map(FuncionarioMapper::of).toList();
    }


    public Funcionario buscarPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário de id %d não encontrado".formatted(id)));
    }

    public List<Funcionario> listar() {

        return funcionarioRepository.findAll();
    }

    public Funcionario atualizar(Funcionario funcionario) {
        if (funcionarioRepository.existsById(funcionario.getId())) {
            funcionario.setId(funcionario.getId());
            return funcionarioRepository.save(funcionario);
        } else {
            throw new EntidadeNaoEncontradaException("Funcionário de id %d não encontrado".formatted(funcionario.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new FuncionarioNaoEncontradaException("Funcionário de id %d não encontrado".formatted(id));
        }

        try {
            funcionarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeDadosException("Não é possível excluir o funcionário, pois existem registros relacionados a ele.");
        }
    }


}
