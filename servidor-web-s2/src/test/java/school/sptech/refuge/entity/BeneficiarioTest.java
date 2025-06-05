package school.sptech.refuge.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.repository.*;
import school.sptech.refuge.service.BeneficiarioService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneficiarioTest {

    @InjectMocks
    private BeneficiarioService beneficiarioService;

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private TipoGeneroRepository tipoGeneroRepository;

    @Mock
    private TipoSexualidadeRepository tipoSexualidadeRepository;

    @Test
    @DisplayName("Cadastrar beneficiário com dados válidos deve retornar beneficiário salvo")
    void cadastrarBeneficiarioQuandoForAcionadoDeveRetonarBeneficiarioCadastradoComSucessoTeste() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1);

        Endereco endereco = new Endereco();
        endereco.setId(2);

        TipoGenero tipoGenero = new TipoGenero();
        tipoGenero.setId(3);

        TipoSexualidade tipoSexualidade = new TipoSexualidade();
        tipoSexualidade.setId(4);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setFuncionario(funcionario);
        beneficiario.setEndereco(endereco);
        beneficiario.setTipoGenero(tipoGenero);
        beneficiario.setTipoSexualidade(tipoSexualidade);

        when(funcionarioRepository.findById(1)).thenReturn(Optional.of(funcionario));
        when(enderecoRepository.findById(2)).thenReturn(Optional.of(endereco));
        when(tipoGeneroRepository.findById(3)).thenReturn(Optional.of(tipoGenero));
        when(tipoSexualidadeRepository.findById(4)).thenReturn(Optional.of(tipoSexualidade));

        when(beneficiarioRepository.save(any(Beneficiario.class))).thenReturn(beneficiario);

        Beneficiario resultado = beneficiarioService.cadastrar(beneficiario);

        assertNotNull(resultado);
        assertEquals(1, resultado.getFuncionario().getId());
        assertEquals(2, resultado.getEndereco().getId());
        assertEquals(3, resultado.getTipoGenero().getId());
        assertEquals(4, resultado.getTipoSexualidade().getId());
        verify(beneficiarioRepository).save(beneficiario);
    }


    @Test
    @DisplayName("Cadastrar beneficiário com ID de funcionário inválido deve lançar exceção")
    void cadastrarBeneficiarioQuandoAcionadoDeveRetornarFuncionarioInvalidoTeste () {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(20);

        Endereco endereco = new Endereco();
        endereco.setId(2);

        TipoGenero tipoGenero = new TipoGenero();
        tipoGenero.setId(3);

        TipoSexualidade tipoSexualidade = new TipoSexualidade();
        tipoSexualidade.setId(4);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setFuncionario(funcionario);
        beneficiario.setEndereco(endereco);
        beneficiario.setTipoGenero(tipoGenero);
        beneficiario.setTipoSexualidade(tipoSexualidade);

        when(funcionarioRepository.findById(20)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () ->
                beneficiarioService.cadastrar(beneficiario)
        );

        assertEquals("Funcionário não encontrado", excecao.getMessage());

        verify(funcionarioRepository).findById(20);
        verify(beneficiarioRepository, never()).save(any());
    }

}