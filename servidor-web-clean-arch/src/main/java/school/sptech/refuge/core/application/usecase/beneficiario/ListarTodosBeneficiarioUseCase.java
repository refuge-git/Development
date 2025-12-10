package school.sptech.refuge.core.application.usecase.beneficiario;

import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.infrastructure.config.redis.RedisService;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosBeneficiarioUseCase {
    /*@Autowired
    public RedisService redisService;*/
    private final BeneficiarioGateway beneficiarioGateway;

    public ListarTodosBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {

        this.beneficiarioGateway = beneficiarioGateway;
    }

    /*public List<BeneficarioListDto> execute() {
        // 1. Tenta ler do cache
        List<BeneficarioListDto> cache = redisService.getBeneficiarios();

        if (cache != null && !cache.isEmpty()) {
            return cache;  // retorna sem acessar o banco
        }

        List<BeneficarioListDto> response = beneficiarioGateway.listarTodos()
                .stream()
                .map(BeneficiarioMapper::fromDomain)
                .collect(Collectors.toList());

        redisService.saveBeneficiarios(response);
        return response;

        }*/

    public List<BeneficarioListDto> execute() {
        return beneficiarioGateway.listarTodos()
                .stream()
                .map(BeneficiarioMapper::fromDomain)
                .collect(Collectors.toList());
    }
    }


