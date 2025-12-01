package school.sptech.refuge.service;

import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.core.adapters.*;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.core.application.usecase.beneficiario.*;
import school.sptech.refuge.core.domain.beneficiario.*;
import school.sptech.refuge.core.domain.endereco.Endereco;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;
//import school.sptech.refuge.entity.*;
//import school.sptech.refuge.exception.BeneficiarioNaoEncontradaException;
//import school.sptech.refuge.exception.FuncionarioNaoEncontradaException;
//import school.sptech.refuge.repository.*;
//import school.sptech.refuge.service.BeneficiarioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.*;
//import static school.sptech.refuge.entity.LocalEnum.PENSAO;
//import static school.sptech.refuge.entity.RacaEnum.BRANCO;
//import static school.sptech.refuge.entity.RacaEnum.PRETO;
//import static school.sptech.refuge.entity.SexoEnum.FEMININO;
//import static school.sptech.refuge.entity.SexoEnum.MASCULINO;
//import static school.sptech.refuge.entity.StatusEnum.ATIVO;
//import static school.sptech.refuge.entity.StatusEnum.INATIVO;

@ExtendWith(MockitoExtension.class)
class BeneficiarioTest {

    @InjectMocks
    private AtualizarBeneficiarioUseCase atualizarBeneficiarioUseCase;
    //private BeneficiarioService beneficiarioService;
    @InjectMocks
    private AtualizarStatusBeneficiarioUseCase atualizarStatusBeneficiarioUseCase;

    @InjectMocks
    private BuscarBeneficiarioUseCase buscarBeneficiarioUseCase;

    @InjectMocks
    private CriarBeneficiarioUseCase criarBeneficiarioUseCase;

    @InjectMocks
    private DeletarBeneficiarioUseCase deletarBeneficiarioUseCase;

    @InjectMocks
    private ListagemBeneficiarioUseCase listagemBeneficiarioUseCase;

    @InjectMocks
    private ListarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase;

    @InjectMocks
    private ListarBeneficiarioPorRacaUseCase listarBeneficiarioPorRacaUseCase;

    @InjectMocks
    private ListarBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusUseCase;

    @InjectMocks
    private ListarBeneficiariosPorNomeRegistroOuSocialUseCase listarBeneficiariosPorNomeRegistroOuSocialUseCase;

    @InjectMocks
    private ListarTodosBeneficiarioUseCase listarTodosBeneficiarioUseCase;

    @InjectMocks
    private PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase;

    @InjectMocks
    private PaginarListagemBeneficiarioPorStatusUseCase paginarListagemBeneficiarioPorStatusUseCase;


    @Mock
    private BeneficiarioGateway beneficiarioGateway;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @Mock
    private EnderecoGateway enderecoGateway;

    @Mock
    private TipoGeneroGateway tipoGeneroGateway;

    @Mock
    private TipoSexualidadeGateway tipoSexualidadeGateway;

    @Mock
    private CondicaoSaudeGateway condicaoSaudeGateway;

    @Mock
    private RegistroAtendimentoGateway registroAtendimentoGateway;

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

        // DTO de entrada
        BeneficiarioRequestDto dto = new BeneficiarioRequestDto();
        dto.setIdFuncionario(1);
        dto.setIdEndereco(2);
        dto.setIdTipoGenero(3);
        dto.setIdTipoSexualidade(4);
        dto.setNomeRegistro("João");
        dto.setRaca("BRANCA");
        dto.setSexo("MASCULINO");
        dto.setLocalDorme("RUA");

        // Entidade que salvar() vai retornar
        Beneficiario entidadeSalva = new Beneficiario();
        entidadeSalva.setId(99);
        entidadeSalva.setFuncionario(funcionario);
        entidadeSalva.setEndereco(endereco);
        entidadeSalva.setTipoGenero(tipoGenero);
        entidadeSalva.setTipoSexualidade(tipoSexualidade);

        when(funcionarioGateway.buscarPorId(1)).thenReturn(Optional.of(funcionario));
        when(enderecoGateway.findById(2)).thenReturn(Optional.of(endereco));
        when(tipoGeneroGateway.buscarPorId(3)).thenReturn(Optional.of(tipoGenero));
        when(tipoSexualidadeGateway.buscarPorId(4)).thenReturn(Optional.of(tipoSexualidade));

        // salvar retorna ENTIDADE, não DTO
        when(beneficiarioGateway.salvar(any(Beneficiario.class))).thenReturn(entidadeSalva);

        BeneficarioListDto resultado = criarBeneficiarioUseCase.execute(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getFuncionario().getId());
        assertEquals(2, resultado.getEndereco().getId());
        assertEquals(3, resultado.getTipoGenero().getId());
        assertEquals(4, resultado.getTipoSexualidade().getId());

        // verifica que salvar foi chamado com uma ENTIDADE
        verify(beneficiarioGateway).salvar(any(Beneficiario.class));
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

        BeneficiarioRequestDto dto = new BeneficiarioRequestDto();
        dto.setIdFuncionario(funcionario.getId());
        dto.setIdEndereco(endereco.getId());
        dto.setIdTipoGenero(tipoGenero.getId());
        dto.setIdTipoSexualidade(tipoSexualidade.getId());

        when(funcionarioGateway.buscarPorId(20)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () ->
                criarBeneficiarioUseCase.execute(dto)
        );

        assertEquals("Funcionário não encontrado", excecao.getMessage());

        verify(funcionarioGateway).buscarPorId(20);
        verify(beneficiarioGateway, never()).salvar(any());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar beneficiário correspondente")
    void buscarPorIdComIdExistenteDeveRetornarBeneficiarioTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        Beneficiario beneficiario = new Beneficiario(2, "Amanda Santos", "Guilherme", LocalDate.of(2001, 11, 3), "34567890122", false, RacaEnum.BRANCO, SexoEnum.FEMININO, "Marcia Gomes", false, LocalEnum.PENSAO, null, "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade);

        when(beneficiarioGateway.buscarPorId(2)).thenReturn(Optional.of(beneficiario));

        BeneficarioListDto resultado = buscarBeneficiarioUseCase.execute(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar beneficiário com ID inexistente")
    void buscarPorIdBeneficiarioComIdInexistenteDeveLancarExcecaoTeste() {
        when(beneficiarioGateway.buscarPorId(99)).thenReturn(Optional.empty());

        assertThrows(BeneficiarioNaoEncontradaException.class, () -> buscarBeneficiarioUseCase.execute(99));
    }

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 beneficiários, deve retornar corretamente")
    void listarQuandoAcionadoComBeneficiariosCadastradasDeveRetornarCorretamenteTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        List<Beneficiario> beneficiarios = List.of(
                new Beneficiario(1, "Amanda Santos", "Guilherme", LocalDate.of(2001, 11, 3), "34567890122", false, RacaEnum.BRANCO, SexoEnum.FEMININO, "Marcia Gomes", false, LocalEnum.PENSAO, null, "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade),

                new Beneficiario(2, "Juliane Santos", null, LocalDate.of(2005, 10, 2), "54971239912", false, RacaEnum.PRETO, SexoEnum.FEMININO, "Tatiana Gomes", false, LocalEnum.PENSAO, null, "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade),

                new Beneficiario(3, "Marian Santos", null, LocalDate.of(1998, 3, 9), "54971239912", false, RacaEnum.PRETO, SexoEnum.FEMININO, "Lucia Gomes", false, LocalEnum.PENSAO, null, "SISA108", StatusEnum.ATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade)
        );

        when(beneficiarioGateway.listarTodos()).thenReturn(beneficiarios);
        List<BeneficarioListDto> listaBeneficiarios = listarTodosBeneficiarioUseCase.execute();
        assertEquals(3, listaBeneficiarios.size());

    }

    @Test
    @DisplayName("Listar quando acionado e não houver beneficiários, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDeBeneficiarioDeveRetornarUmaColecaoVaziaTeste() {

        when(beneficiarioGateway.listarTodos()).thenReturn(Collections.emptyList());
        List<BeneficarioListDto> resultado = listarTodosBeneficiarioUseCase.execute();
        assertTrue(resultado.isEmpty());
    }


    @Test
    @DisplayName("Quando listarNomeSocial for acionado, deve retornar uma lista com os 2 beneficiários correspondentes ao nome social na pesquisa")
    public void listarNomeSocialQuandoAcionadoDeveRetornarListaCom2ResultadosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        List<Beneficiario> beneficiarios = List.of(
                new Beneficiario(1, "Anderson Santos", "Maria", LocalDate.of(2001, 11, 3), "34567890122", false, RacaEnum.BRANCO, SexoEnum.FEMININO, "Marcia Gomes", false, LocalEnum.PENSAO, null, "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade),

                new Beneficiario(2, "Luiz Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, RacaEnum.PRETO, SexoEnum.FEMININO, "Tatiana Gomes", false, LocalEnum.PENSAO, null, "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade)
        );
        when(beneficiarioGateway.buscarPorNomeRegistroOuNomeSocial("maria"))
                .thenReturn(beneficiarios);

        List<Beneficiario> resultado = beneficiarioGateway.buscarPorNomeRegistroOuNomeSocial("maria");

        assertEquals(2, resultado.size());
        assertTrue(resultado.get(0).getNomeSocial().toLowerCase().contains("maria"));
    }

    @Test
    @DisplayName("Quando listarNomeRegistro for acionado, deve retornar uma lista com os 2 beneficiários correspondentes ao nome de registro na pesquisa")
    public void listarNomeRegistroQuandoAcionadoDeveRetornarListaCom2ResultadosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        List<Beneficiario> beneficiarios = List.of(
                new Beneficiario(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3), "34567890122", false, RacaEnum.BRANCO, SexoEnum.FEMININO, "Marcia Gomes", false, LocalEnum.PENSAO, null, "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade),

                new Beneficiario(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, RacaEnum.PRETO, SexoEnum.FEMININO, "Tatiana Gomes", false, LocalEnum.PENSAO, null, "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade)
        );
        when(beneficiarioGateway.buscarPorNomeRegistroOuNomeSocial("felipe"))
                .thenReturn(beneficiarios);

        List<BeneficarioListDto> resultado = listarBeneficiariosPorNomeRegistroOuSocialUseCase.execute("felipe");

        assertEquals(2, resultado.size());
        assertTrue(resultado.get(0).getNomeRegistro().toLowerCase().contains("felipe"));
    }


    @Test
    @DisplayName("Quando remover for acionado deve remover beneficiário existente com sucesso")
    void removerQuandoAcionadoDeveRemoverFuncionarioExistenteSemErrosTeste() {
        when(beneficiarioGateway.existePorId(1)).thenReturn(true);

        deletarBeneficiarioUseCase.execute(1);

        verify(beneficiarioGateway, times(1)).existePorId(1);
    }

    @DisplayName("Quando removerPorId for acionado com ID inexistente, deve lançar BeneficiarioNaoEncontradaException")
    @Test
    void removerPorIdQuandoIdNaoExisteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
        when(beneficiarioGateway.existePorId(99)).thenReturn(false);

        BeneficiarioNaoEncontradaException exception = assertThrows(
                BeneficiarioNaoEncontradaException.class,
                () -> deletarBeneficiarioUseCase.execute(99)
        );

        assertEquals("Beneficiário não encontrado para exclusão", exception.getMessage());
    }


    @Test
    @DisplayName("Quando atualizar for acionado com ID existente, deve salvar e retornar o beneficiário atualizado")
    void atualizarQuandoForAcionadoEIdExisteDeveRetornarBeneficiarioAtualizadoTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");


        Beneficiario beneficiario = new Beneficiario(2, "João Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, RacaEnum.PRETO, SexoEnum.FEMININO, "Tatiana Gomes", false, LocalEnum.PENSAO, null, "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade);

         BeneficiarioAtualizacaoDto dto = new BeneficiarioAtualizacaoDto();
         dto.setNomeRegistro("João Rocha");
         dto.setNomeSocial("Maria Julia");
         dto.setDtNasc(LocalDate.of(2005, 10, 2));
         dto.setCpf("54971239912");
            dto.setRaca("PRETO");
            dto.setSexo("FEMININO");
            dto.setNomeMae("Tatiana Gomes");
            dto.setEstrangeiro(false);
            dto.setEgressoPrisional(false);
            dto.setLocalDorme("PENSAO");
            dto.setFotoPerfil(null);
            dto.setSisa("SISA106");
            dto.setObservacao("Pessoa com bom comportamento");
            dto.setIdFuncionario(funcionario.getId());
            dto.setIdEndereco(endereco.getId());
            dto.setIdTipoGenero(tipoGenero.getId());
            dto.setIdTipoSexualidade(tipoSexualidade.getId());



        when(beneficiarioGateway.existePorId(2)).thenReturn(true);
        when(beneficiarioGateway.salvar(beneficiario)).thenReturn(beneficiario);

        BeneficarioListDto resultado = atualizarBeneficiarioUseCase.execute(2, dto);

        assertNotNull(resultado);
        assertEquals("João Rocha", resultado.getNomeRegistro());
        verify(beneficiarioGateway).salvar(beneficiario);
    }


    @DisplayName("Quando atualizar for acionado com ID inexistente, deve lançar BeneficiarioNaoEncontradaException")
    @Test
    void atualizarQuandoIdNaoExisteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        Beneficiario beneficiario = new Beneficiario(55, "João Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, RacaEnum.PRETO, SexoEnum.FEMININO, "Tatiana Gomes", false, LocalEnum.PENSAO, null, "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGenero, tipoSexualidade);

        when(beneficiarioGateway.existePorId(55)).thenReturn(false);

        BeneficiarioAtualizacaoDto dto = new BeneficiarioAtualizacaoDto();
        dto.setNomeRegistro("João Rocha");
        dto.setNomeSocial("Maria Julia");
        dto.setDtNasc(LocalDate.of(2005, 10, 2));
        dto.setCpf("54971239912");
        dto.setRaca("PRETO");
        dto.setSexo("FEMININO");
        dto.setNomeMae("Tatiana Gomes");
        dto.setEstrangeiro(false);
        dto.setEgressoPrisional(false);
        dto.setLocalDorme("PENSAO");
        dto.setFotoPerfil(null);
        dto.setSisa("SISA106");
        dto.setObservacao("Pessoa com bom comportamento");
        dto.setIdFuncionario(funcionario.getId());
        dto.setIdEndereco(endereco.getId());
        dto.setIdTipoGenero(tipoGenero.getId());
        dto.setIdTipoSexualidade(tipoSexualidade.getId());


        BeneficiarioNaoEncontradaException exception = assertThrows(
                BeneficiarioNaoEncontradaException.class,
                () -> atualizarBeneficiarioUseCase.execute(55, dto)
        );

        assertEquals("Beneficiario de id 55 não encontrado", exception.getMessage());
    }

//
//    @Test
//    @DisplayName("Quando listarPorSexo for acionado deve retornar lista de beneficiários com sexo correspondente")
//    void listarPorSexoQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
//        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");
//
//        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
//                "Ap 10 A", "Centro", "01310-000", "São Paulo");
//
//        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");
//
//        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");
//
//        Beneficiario b1 = new Beneficiario(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
//                "34567890122", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Marcia Gomes", false, LocalEnum.PENSAO, null,
//                "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
//                funcionario, endereco, tipoGenero, tipoSexualidade);
//
//        Beneficiario b2 = new Beneficiario(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
//                "54971239912", false, RacaEnum.PRETO, SexoEnum.MASCULINO, "Tatiana Gomes", false, LocalEnum.PENSAO, null,
//                "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
//                funcionario, endereco, tipoGenero, tipoSexualidade);
//
//        List<Beneficiario> beneficiarios = List.of(b1, b2);
//
//        SexoEnum sexo = SexoEnum.MASCULINO;
//        when(beneficiarioGateway.buscarPorSexo(sexo)).thenReturn(beneficiarios);
//
//        List<Beneficiario> resultado = beneficiarioService.listarPorSexo(sexo);
//
//        assertFalse(resultado.isEmpty());
//        assertEquals(2, resultado.size());
//    }

    @Test
    @DisplayName("Quando listarPorRaca for acionado deve retornar lista de beneficiários com raça correspondente")
    void listarPorRacaQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo");

        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Heterossexual", "Atração pelo sexo oposto");

        Beneficiario b1 = new Beneficiario(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
                "34567890122", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Marcia Gomes", false, LocalEnum.PENSAO, null,
                "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGenero, tipoSexualidade);

        Beneficiario b2 = new Beneficiario(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
                "54971239912", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Tatiana Gomes", false, LocalEnum.PENSAO, null,
                "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGenero, tipoSexualidade);

        List<Beneficiario> beneficiarios = List.of(b1, b2);

        RacaEnum raca = RacaEnum.BRANCO;
        when(beneficiarioGateway.buscarPorRaca(raca.name())).thenReturn(beneficiarios);

        List<BeneficarioListDto> resultado = listarBeneficiarioPorRacaUseCase.execute(raca.name());

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }



//    @Test
//    @DisplayName("Quando listarPorTipoSexualidade for acionado com parâmetro do tipo de sexaulidade correto deve retornar lista de beneficiários correspondentes")
//    void listarPorTipoSexualidadeQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
//        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");
//
//        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
//                "Ap 10 A", "Centro", "01310-000", "São Paulo");
//
//        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");
//
//        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Homossexual", "Atração pelo sexo oposto");
//
//        Beneficiario b1 = new Beneficiario(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
//                "34567890122", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Marcia Gomes", false, LocalEnum.PENSAO, null,
//                "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
//                funcionario, endereco, tipoGenero, tipoSexualidade);
//
//        Beneficiario b2 = new Beneficiario(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
//                "54971239912", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Tatiana Gomes", false, LocalEnum.PENSAO, null,
//                "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
//                funcionario, endereco, tipoGenero, tipoSexualidade);
//
//        List<Beneficiario> beneficiarios = List.of(b1, b2);
//        String nome = "homossexual";
//        when(beneficiarioGateway.buscarPorSexualidade(nome)).thenReturn(beneficiarios);
//
//        List<Beneficiario> resultado = bu.listarPorTipoSexualidade(nome);
//
//        assertFalse(resultado.isEmpty());
//        assertEquals(2, resultado.size());
//    }
//
//    @Test
//    @DisplayName("Quando listarPorTipoSexualidade for acionado com tipo de sexualidade inexistente deve retornar uma lista vazia")
//    void listarPorTipoSexualidadeQuandoForAcionadoComTipoInexistenteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
//        String nome = "Inexistente";
//        when(beneficiarioGateway.findByNomeTipoSexualidade(nome)).thenReturn(Collections.emptyList());
//
//        BeneficiarioNaoEncontradaException exception = assertThrows(BeneficiarioNaoEncontradaException.class, () ->
//                beneficiarioService.listarPorTipoSexualidade(nome));
//
//        assertEquals("Nenhum beneficiário com sexualidade 'Inexistente' foi encontrado.", exception.getMessage());
//
//    }

//    @Test
//    @DisplayName("Quando listarPorTipoGenero for acionado com parâmetro do tipo de gênero correto deve retornar lista de beneficiários correspondentes")
//    void listarPorTipoGeneroQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
//        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");
//
//        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
//                "Ap 10 A", "Centro", "01310-000", "São Paulo");
//
//        TipoGenero tipoGenero = new TipoGenero(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");
//
//        TipoSexualidade tipoSexualidade = new TipoSexualidade(1, "Homossexual", "Atração pelo sexo oposto");
//
//        Beneficiario b1 = new Beneficiario(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
//                "34567890122", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Marcia Gomes", false, LocalEnum.PENSAO, null,
//                "SISA104", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
//                funcionario, endereco, tipoGenero, tipoSexualidade);
//
//        Beneficiario b2 = new Beneficiario(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
//                "54971239912", false, RacaEnum.BRANCO, SexoEnum.MASCULINO, "Tatiana Gomes", false, LocalEnum.PENSAO, null,
//                "SISA106", StatusEnum.INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
//                funcionario, endereco, tipoGenero, tipoSexualidade);
//
//        List<Beneficiario> beneficiarios = List.of(b1, b2);
//        String nome = "cisgênero";
//        when(beneficiarioGateway.buscarPorSexo(nome)).thenReturn(beneficiarios);
//
//        List<Beneficiario> resultado = bu.listarPorTipoGenero(nome);
//
//        assertFalse(resultado.isEmpty());
//        assertEquals(2, resultado.size());
//    }

//    @Test
//    @DisplayName("Quando listarPorTipoGenero for acionado com tipo de sexualidade inexistente deve retornar uma lista vazia")
//    void listarPorTipoGeneroQuandoForAcionadoComTipoInexistenteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
//        String nome = "Inexistente";
//        when(beneficiarioGateway.findByNomeTipoGenero(nome)).thenReturn(Collections.emptyList());
//
//        BeneficiarioNaoEncontradaException exception = assertThrows(BeneficiarioNaoEncontradaException.class, () ->
//                beneficiarioService.listarPorTipoGenero(nome));
//
//        assertEquals("Nenhum beneficiário com gênero 'Inexistente' foi encontrado.", exception.getMessage());
//
//    }



}