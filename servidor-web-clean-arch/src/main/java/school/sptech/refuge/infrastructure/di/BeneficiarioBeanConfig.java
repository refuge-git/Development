package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.usecase.beneficiario.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoJpaAdapter;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroJpaAdapter;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeJpaAdapter;
import school.sptech.refuge.infrastructure.config.bucketS3.S3UploadService;

@Configuration
public class BeneficiarioBeanConfig {

    @Bean
    public AtualizarStatusBeneficiarioUseCase atualizarStatusBeneficiarioUseCase(
            BeneficiarioGateway beneficiarioGateway
    ) {
        return new AtualizarStatusBeneficiarioUseCase(beneficiarioGateway);
    }

    @Bean
    public CriarBeneficiarioUseCase criarBeneficiarioUseCase(BeneficiarioJpaAdapter adapter,
                                                             FuncionarioJpaAdapter funcionarioAdapter,
                                                             EnderecoJpaAdapter enderecoAdapter,
                                                             TipoGeneroJpaAdapter tipoGeneroAdapter,
                                                             TipoSexualidadeJpaAdapter tipoSexualidadeAdapter,
                                                             S3UploadService s3UploadService) {
        return new CriarBeneficiarioUseCase(adapter, funcionarioAdapter, enderecoAdapter, tipoGeneroAdapter, tipoSexualidadeAdapter, s3UploadService);
    }

    @Bean
    public ListarTodosBeneficiarioUseCase listarTodosBeneficiarioUseCase(BeneficiarioJpaAdapter adapter) {
        return new ListarTodosBeneficiarioUseCase(adapter);
    }

    @Bean
    public BuscarBeneficiarioUseCase buscarBeneficiarioPorIdUseCase(BeneficiarioJpaAdapter adapter) {
        return new BuscarBeneficiarioUseCase(adapter);
    }

    @Bean
    public AtualizarBeneficiarioUseCase atualizarBeneficiarioUseCase(BeneficiarioJpaAdapter adapter,
                                                                     FuncionarioJpaAdapter funcionarioAdapter,
                                                                     EnderecoJpaAdapter enderecoAdapter,
                                                                     TipoGeneroJpaAdapter tipoGeneroAdapter,
                                                                     TipoSexualidadeJpaAdapter tipoSexualidadeAdapter) {
        return new AtualizarBeneficiarioUseCase(adapter, funcionarioAdapter, enderecoAdapter, tipoGeneroAdapter, tipoSexualidadeAdapter);
    }

    @Bean
    public DeletarBeneficiarioUseCase deletarBeneficiarioUseCase(BeneficiarioJpaAdapter adapter) {
        return new DeletarBeneficiarioUseCase(adapter);
    }

    @Bean
    public ListarBeneficiarioPorRacaUseCase listarBeneficiarioPorRacaUseCase(BeneficiarioJpaAdapter adapter) {
        return new ListarBeneficiarioPorRacaUseCase(adapter);
    }

    @Bean
    public ListarBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusUseCase(BeneficiarioJpaAdapter adapter) {
        return new ListarBeneficiarioPorStatusUseCase(adapter);
    }

    @Bean
    public ListarBeneficiariosPorNomeRegistroOuSocialUseCase listarBeneficiariosPorNomeRegistroOuSocialUseCase(BeneficiarioJpaAdapter adapter) {
        return new ListarBeneficiariosPorNomeRegistroOuSocialUseCase(adapter);
    }

    @Bean
    public ListarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase listarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase(BeneficiarioJpaAdapter adapter) {
        return new ListarBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase(adapter);
    }

    @Bean
    public  ListagemBeneficiarioUseCase listagemBeneficiarioUseCase(BeneficiarioJpaAdapter adapter){
        return new ListagemBeneficiarioUseCase(adapter);
    }

    @Bean
    public PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase paginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase(BeneficiarioJpaAdapter adapter){
        return new PaginarListagemBeneficiarioPorFrequenciaNoDiaDaSemanaUseCase(adapter);
    }

    @Bean
    public PaginarListagemBeneficiarioPorStatusUseCase paginarListagemBeneficiarioPorStatusUseCase(BeneficiarioJpaAdapter adapter){
        return new PaginarListagemBeneficiarioPorStatusUseCase(adapter);
    }
}
