package school.sptech.refuge.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.refuge.core.application.usecase.beneficiario.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoJpaAdapter;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioJpaAdapter;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroJpaAdapter;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeJpaAdapter;

@Configuration
public class BeneficiarioBeanConfig {

    @Bean
    public CriarBeneficiarioUseCase criarBeneficiarioUseCase(BeneficiarioJpaAdapter adapter,
                                                             FuncionarioJpaAdapter funcionarioAdapter,
                                                             EnderecoJpaAdapter enderecoAdapter,
                                                             TipoGeneroJpaAdapter tipoGeneroAdapter,
                                                             TipoSexualidadeJpaAdapter tipoSexualidadeAdapter) {
        return new CriarBeneficiarioUseCase(adapter, funcionarioAdapter, enderecoAdapter, tipoGeneroAdapter, tipoSexualidadeAdapter);
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
}
