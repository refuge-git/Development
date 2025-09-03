package school.sptech.refuge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.antes.entity.*;
import school.sptech.refuge.antes.repository.*;
import school.sptech.refuge.core.application.exception.BeneficiarioNaoEncontradaException;
import school.sptech.refuge.antes.service.BeneficiarioService;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeRepository;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaRepository;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroJpaRepository;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static school.sptech.refuge.core.domain.beneficiario.LocalEnum.PENSAO;
import static school.sptech.refuge.core.domain.beneficiario.RacaEnum.BRANCO;
import static school.sptech.refuge.core.domain.beneficiario.RacaEnum.PRETO;
import static school.sptech.refuge.core.domain.beneficiario.SexoEnum.FEMININO;
import static school.sptech.refuge.core.domain.beneficiario.SexoEnum.MASCULINO;
import static school.sptech.refuge.core.domain.beneficiario.StatusEnum.ATIVO;
import static school.sptech.refuge.core.domain.beneficiario.StatusEnum.INATIVO;

@ExtendWith(MockitoExtension.class)
class BeneficiarioEntityTest {

    @InjectMocks
    private BeneficiarioService beneficiarioService;

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @Mock
    private FuncionarioJpaRepository funcionarioJpaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private TipoGeneroJpaRepository tipoGeneroJpaRepository;

    @Mock
    private TipoSexualidadeRepository tipoSexualidadeRepository;

    @Mock
    private CondicaoSaudeRepository condicaoSaudeRepository;

    @Mock
    private RegistroAtendimentoRepository registroAtendimentoRepository;

    @Test
    @DisplayName("Cadastrar beneficiário com dados válidos deve retornar beneficiário salvo")
    void cadastrarBeneficiarioQuandoForAcionadoDeveRetonarBeneficiarioCadastradoComSucessoTeste() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1);

        Endereco endereco = new Endereco();
        endereco.setId(2);

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity();
        tipoGeneroEntity.setId(3);

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity();
        tipoSexualidadeEntity.setId(4);

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity();
        beneficiarioEntity.setFuncionario(funcionario);
        beneficiarioEntity.setEndereco(endereco);
        beneficiarioEntity.setTipoGenero(tipoGeneroEntity);
        beneficiarioEntity.setTipoSexualidade(tipoSexualidadeEntity);

        when(funcionarioJpaRepository.findById(1)).thenReturn(Optional.of(funcionario));
        when(enderecoRepository.findById(2)).thenReturn(Optional.of(endereco));
        when(tipoGeneroJpaRepository.findById(3)).thenReturn(Optional.of(tipoGeneroEntity));
        when(tipoSexualidadeRepository.findById(4)).thenReturn(Optional.of(tipoSexualidadeEntity));

        when(beneficiarioRepository.save(any(BeneficiarioEntity.class))).thenReturn(beneficiarioEntity);

        BeneficiarioEntity resultado = beneficiarioService.cadastrar(beneficiarioEntity);

        assertNotNull(resultado);
        assertEquals(1, resultado.getFuncionario().getId());
        assertEquals(2, resultado.getEndereco().getId());
        assertEquals(3, resultado.getTipoGenero().getId());
        assertEquals(4, resultado.getTipoSexualidade().getId());
        verify(beneficiarioRepository).save(beneficiarioEntity);
    }


    @Test
    @DisplayName("Cadastrar beneficiário com ID de funcionário inválido deve lançar exceção")
    void cadastrarBeneficiarioQuandoAcionadoDeveRetornarFuncionarioInvalidoTeste () {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(20);

        Endereco endereco = new Endereco();
        endereco.setId(2);

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity();
        tipoGeneroEntity.setId(3);

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity();
        tipoSexualidadeEntity.setId(4);

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity();
        beneficiarioEntity.setFuncionario(funcionario);
        beneficiarioEntity.setEndereco(endereco);
        beneficiarioEntity.setTipoGenero(tipoGeneroEntity);
        beneficiarioEntity.setTipoSexualidade(tipoSexualidadeEntity);

        when(funcionarioJpaRepository.findById(20)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () ->
                beneficiarioService.cadastrar(beneficiarioEntity)
        );

        assertEquals("Funcionário não encontrado", excecao.getMessage());

        verify(funcionarioJpaRepository).findById(20);
        verify(beneficiarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Quando buscarPorId for acionado deve retornar beneficiário correspondente")
    void buscarPorIdComIdExistenteDeveRetornarBeneficiarioTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity(2, "Amanda Santos", "Guilherme", LocalDate.of(2001, 11, 3), "34567890122", false, BRANCO, FEMININO, "Marcia Gomes", false, PENSAO, null, "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        when(beneficiarioRepository.findById(2)).thenReturn(Optional.of(beneficiarioEntity));

        BeneficiarioEntity resultado = beneficiarioService.buscarPorId(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar beneficiário com ID inexistente")
    void buscarPorIdBeneficiarioComIdInexistenteDeveLancarExcecaoTeste() {
        when(beneficiarioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(BeneficiarioNaoEncontradaException.class, () -> beneficiarioService.buscarPorId(99));
    }

    @Test
    @DisplayName("Quando listar for acionado com tabela preenchida com 3 beneficiários, deve retornar corretamente")
    void listarQuandoAcionadoComBeneficiariosCadastradasDeveRetornarCorretamenteTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        List<BeneficiarioEntity> beneficiarioEntities = List.of(
                new BeneficiarioEntity(1, "Amanda Santos", "Guilherme", LocalDate.of(2001, 11, 3), "34567890122", false, BRANCO, FEMININO, "Marcia Gomes", false, PENSAO, null, "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity),

                new BeneficiarioEntity(2, "Juliane Santos", null, LocalDate.of(2005, 10, 2), "54971239912", false, PRETO, FEMININO, "Tatiana Gomes", false, PENSAO, null, "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity),

                new BeneficiarioEntity(3, "Marian Santos", null, LocalDate.of(1998, 3, 9), "54971239912", false, PRETO, FEMININO, "Lucia Gomes", false, PENSAO, null, "SISA108", ATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity)
        );

        when(beneficiarioRepository.findAll()).thenReturn(beneficiarioEntities);
        List<BeneficiarioEntity> listaBeneficiarioEntities = beneficiarioService.listar();
        assertEquals(3, listaBeneficiarioEntities.size());

    }

    @Test
    @DisplayName("Listar quando acionado e não houver beneficiários, deve retornar lista vazia")
    void listarQuandoAcionadoComTabelaVaziaDeBeneficiarioDeveRetornarUmaColecaoVaziaTeste() {

        when(beneficiarioRepository.findAll()).thenReturn(Collections.emptyList());
        List<BeneficiarioEntity> resultado = beneficiarioService.listar();
        assertTrue(resultado.isEmpty());
    }


    @Test
    @DisplayName("Quando listarNomeSocial for acionado, deve retornar uma lista com os 2 beneficiários correspondentes ao nome social na pesquisa")
    public void listarNomeSocialQuandoAcionadoDeveRetornarListaCom2ResultadosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        List<BeneficiarioEntity> beneficiarioEntities = List.of(
                new BeneficiarioEntity(1, "Anderson Santos", "Maria", LocalDate.of(2001, 11, 3), "34567890122", false, BRANCO, FEMININO, "Marcia Gomes", false, PENSAO, null, "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity),

                new BeneficiarioEntity(2, "Luiz Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, PRETO, FEMININO, "Tatiana Gomes", false, PENSAO, null, "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity)
        );
        when(beneficiarioRepository.findByNomeSocialContainingIgnoreCase("maria"))
                .thenReturn(beneficiarioEntities);

        List<BeneficiarioEntity> resultado = beneficiarioService.listarNomeSocial("maria");

        assertEquals(2, resultado.size());
        assertTrue(resultado.get(0).getNomeSocial().toLowerCase().contains("maria"));
    }

    @Test
    @DisplayName("Quando listarNomeRegistro for acionado, deve retornar uma lista com os 2 beneficiários correspondentes ao nome de registro na pesquisa")
    public void listarNomeRegistroQuandoAcionadoDeveRetornarListaCom2ResultadosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        List<BeneficiarioEntity> beneficiarioEntities = List.of(
                new BeneficiarioEntity(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3), "34567890122", false, BRANCO, FEMININO, "Marcia Gomes", false, PENSAO, null, "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity),

                new BeneficiarioEntity(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, PRETO, FEMININO, "Tatiana Gomes", false, PENSAO, null, "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity)
        );
        when(beneficiarioRepository.findByNomeRegistroContainingIgnoreCase("felipe"))
                .thenReturn(beneficiarioEntities);

        List<BeneficiarioEntity> resultado = beneficiarioService.listarNomeRegistro("felipe");

        assertEquals(2, resultado.size());
        assertTrue(resultado.get(0).getNomeRegistro().toLowerCase().contains("felipe"));
    }


    @Test
    @DisplayName("Quando remover for acionado deve remover beneficiário existente com sucesso")
    void removerQuandoAcionadoDeveRemoverFuncionarioExistenteSemErrosTeste() {
        when(beneficiarioRepository.existsById(1)).thenReturn(true);

        beneficiarioService.removerPorId(1);

        verify(beneficiarioRepository, times(1)).deleteById(1);
    }

    @DisplayName("Quando removerPorId for acionado com ID inexistente, deve lançar BeneficiarioNaoEncontradaException")
    @Test
    void removerPorIdQuandoIdNaoExisteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
        when(beneficiarioRepository.existsById(99)).thenReturn(false);

        BeneficiarioNaoEncontradaException exception = assertThrows(
                BeneficiarioNaoEncontradaException.class,
                () -> beneficiarioService.removerPorId(99)
        );

        assertEquals("Beneficiário de id 99 não encontrado", exception.getMessage());
    }


    @Test
    @DisplayName("Quando atualizar for acionado com ID existente, deve salvar e retornar o beneficiário atualizado")
    void atualizarQuandoForAcionadoEIdExisteDeveRetornarBeneficiarioAtualizadoTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");


        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity(2, "João Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, PRETO, FEMININO, "Tatiana Gomes", false, PENSAO, null, "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        when(beneficiarioRepository.existsById(2)).thenReturn(true);
        when(beneficiarioRepository.save(beneficiarioEntity)).thenReturn(beneficiarioEntity);

        BeneficiarioEntity resultado = beneficiarioService.atualizar(beneficiarioEntity);

        assertNotNull(resultado);
        assertEquals("João Rocha", resultado.getNomeRegistro());
        verify(beneficiarioRepository).save(beneficiarioEntity);
    }


    @DisplayName("Quando atualizar for acionado com ID inexistente, deve lançar BeneficiarioNaoEncontradaException")
    @Test
    void atualizarQuandoIdNaoExisteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity(55, "João Rocha", "Maria Julia", LocalDate.of(2005, 10, 2), "54971239912", false, PRETO, FEMININO, "Tatiana Gomes", false, PENSAO, null, "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        when(beneficiarioRepository.existsById(55)).thenReturn(false);

        BeneficiarioNaoEncontradaException exception = assertThrows(
                BeneficiarioNaoEncontradaException.class,
                () -> beneficiarioService.atualizar(beneficiarioEntity)
        );

        assertEquals("Beneficiario de id 55 não encontrado", exception.getMessage());
    }


    @Test
    @DisplayName("Quando listarPorSexo for acionado deve retornar lista de beneficiários com sexo correspondente")
    void listarPorSexoQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        BeneficiarioEntity b1 = new BeneficiarioEntity(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
                "34567890122", false, BRANCO, MASCULINO, "Marcia Gomes", false, PENSAO, null,
                "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        BeneficiarioEntity b2 = new BeneficiarioEntity(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
                "54971239912", false, PRETO, MASCULINO, "Tatiana Gomes", false, PENSAO, null,
                "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        List<BeneficiarioEntity> beneficiarioEntities = List.of(b1, b2);

        SexoEnum sexo = MASCULINO;
        when(beneficiarioRepository.findBySexo(sexo)).thenReturn(beneficiarioEntities);

        List<BeneficiarioEntity> resultado = beneficiarioService.listarPorSexo(sexo);

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Quando listarPorRaca for acionado deve retornar lista de beneficiários com raça correspondente")
    void listarPorRacaQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Heterossexual", "Atração pelo sexo oposto");

        BeneficiarioEntity b1 = new BeneficiarioEntity(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
                "34567890122", false, BRANCO, MASCULINO, "Marcia Gomes", false, PENSAO, null,
                "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        BeneficiarioEntity b2 = new BeneficiarioEntity(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
                "54971239912", false, BRANCO, MASCULINO, "Tatiana Gomes", false, PENSAO, null,
                "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        List<BeneficiarioEntity> beneficiarioEntities = List.of(b1, b2);

        RacaEnum raca = BRANCO;
        when(beneficiarioRepository.findByRaca(raca)).thenReturn(beneficiarioEntities);

        List<BeneficiarioEntity> resultado = beneficiarioService.listarPorRaca(raca);

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }


    @Test
    @DisplayName("Quando listarPorTipoSexualidade for acionado com parâmetro do tipo de sexaulidade correto deve retornar lista de beneficiários correspondentes")
    void listarPorTipoSexualidadeQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Homossexual", "Atração pelo sexo oposto");

        BeneficiarioEntity b1 = new BeneficiarioEntity(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
                "34567890122", false, BRANCO, MASCULINO, "Marcia Gomes", false, PENSAO, null,
                "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        BeneficiarioEntity b2 = new BeneficiarioEntity(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
                "54971239912", false, BRANCO, MASCULINO, "Tatiana Gomes", false, PENSAO, null,
                "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        List<BeneficiarioEntity> beneficiarioEntities = List.of(b1, b2);
        String nome = "homossexual";
        when(beneficiarioRepository.findByNomeTipoSexualidade(nome)).thenReturn(beneficiarioEntities);

        List<BeneficiarioEntity> resultado = beneficiarioService.listarPorTipoSexualidade(nome);

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Quando listarPorTipoSexualidade for acionado com tipo de sexualidade inexistente deve retornar uma lista vazia")
    void listarPorTipoSexualidadeQuandoForAcionadoComTipoInexistenteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
        String nome = "Inexistente";
        when(beneficiarioRepository.findByNomeTipoSexualidade(nome)).thenReturn(Collections.emptyList());

        BeneficiarioNaoEncontradaException exception = assertThrows(BeneficiarioNaoEncontradaException.class, () ->
                beneficiarioService.listarPorTipoSexualidade(nome));

        assertEquals("Nenhum beneficiário com sexualidade 'Inexistente' foi encontrado.", exception.getMessage());

    }

    @Test
    @DisplayName("Quando listarPorTipoGenero for acionado com parâmetro do tipo de gênero correto deve retornar lista de beneficiários correspondentes")
    void listarPorTipoGeneroQuandoAcionadoDeveRetornarListaDeBeneficiariosCorrespondentesTeste() {
        Funcionario funcionario = new Funcionario(1, "João", "12345678901", "(11)999999999", "joao@gmail.com", "senha123");

        Endereco endereco = new Endereco(1, "Rua", "Alameda Santos", 456,
                "Ap 10 A", "Centro", "01310-000", "São Paulo", "SP");

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity(1, "Cisgênero", "Pessoa cuja identidade de gênero corresponde ao sexo atribuído no nascimento");

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity(1, "Homossexual", "Atração pelo sexo oposto");

        BeneficiarioEntity b1 = new BeneficiarioEntity(1, "Felipe Santos", "Maria", LocalDate.of(2001, 11, 3),
                "34567890122", false, BRANCO, MASCULINO, "Marcia Gomes", false, PENSAO, null,
                "SISA104", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        BeneficiarioEntity b2 = new BeneficiarioEntity(2, "Felipe Rocha", "Maria Julia", LocalDate.of(2005, 10, 2),
                "54971239912", false, BRANCO, MASCULINO, "Tatiana Gomes", false, PENSAO, null,
                "SISA106", INATIVO, LocalDateTime.parse("2025-06-05T10:50:26"), "Pessoa com bom comportamento",
                funcionario, endereco, tipoGeneroEntity, tipoSexualidadeEntity);

        List<BeneficiarioEntity> beneficiarioEntities = List.of(b1, b2);
        String nome = "cisgênero";
        when(beneficiarioRepository.findByNomeTipoGenero(nome)).thenReturn(beneficiarioEntities);

        List<BeneficiarioEntity> resultado = beneficiarioService.listarPorTipoGenero(nome);

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Quando listarPorTipoGenero for acionado com tipo de sexualidade inexistente deve retornar uma lista vazia")
    void listarPorTipoGeneroQuandoForAcionadoComTipoInexistenteDeveLancarBeneficiarioNaoEncontradaExceptionTeste() {
        String nome = "Inexistente";
        when(beneficiarioRepository.findByNomeTipoGenero(nome)).thenReturn(Collections.emptyList());

        BeneficiarioNaoEncontradaException exception = assertThrows(BeneficiarioNaoEncontradaException.class, () ->
                beneficiarioService.listarPorTipoGenero(nome));

        assertEquals("Nenhum beneficiário com gênero 'Inexistente' foi encontrado.", exception.getMessage());

    }



}