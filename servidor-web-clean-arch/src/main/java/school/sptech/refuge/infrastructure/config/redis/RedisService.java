package school.sptech.refuge.infrastructure.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;

import java.time.Duration;
import java.util.List;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String CACHE_KEY = "lista-beneficiarios";

    // 1. Buscar no cache
    public List<BeneficarioListDto> getBeneficiarios() {
        return (List<BeneficarioListDto>) redisTemplate.opsForValue().get(CACHE_KEY);
    }

    // 2. Salvar no cache
    public void saveBeneficiarios(List<BeneficarioListDto> lista) {
        redisTemplate.opsForValue().set(
                CACHE_KEY,
                lista,
                Duration.ofSeconds(30)
        );
    }
}
