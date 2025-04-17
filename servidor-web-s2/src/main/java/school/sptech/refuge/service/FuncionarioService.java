package school.sptech.refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.dto.funcionario.FuncionarioMapper;
import school.sptech.refuge.dto.funcionario.FuncionarioTokenDto;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.exeption.EntidadeNaoEncontradaException;
import school.sptech.refuge.exeption.FuncionarioNaoEncontradaException;
import school.sptech.refuge.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void criar(Funcionario funcionario) {
        String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        this.funcionarioRepository.save(funcionario);
    }

    public FuncionarioTokenDto autenticar(Funcionario funcionario) {
        final UsernamePasswordAuthenticationToken credetials = new UsernamePasswordAuthenticationToken(funcionario.getEmail(), funcionario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credetials);

        Funcionario funcionarioAutenticado = funcionarioRepository.findByEmail(funcionario.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Emial dousuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FuncionarioMapper.of(funcionarioAutenticado, token);
    }

    public List<FuncionarioListDto> listarTodos() {
        List<Funcionario> funcionariosEncontrados = funcionarioRepository.findAll();
        return funcionariosEncontrados.stream().map(FuncionarioMapper::of).toList();
    }




    public Funcionario cadastrar(Funcionario funcionario) {

        return funcionarioRepository.save(funcionario);
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
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Funcionário de id %d não encontrado".formatted(id));
        }
    }


}
