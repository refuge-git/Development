package school.sptech.refuge.infrastructure.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisService {
    @Autowired
    private ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String CACHE_KEY = "lista-beneficiarios";

    public List<BeneficarioListDto> getBeneficiarios() {
        Object raw = redisTemplate.opsForValue().get(CACHE_KEY);

        if (raw == null) return null;

        List<?> list = (List<?>) raw;

        System.out.println("Retornando do cache");

        return list.stream()
                .map(obj -> objectMapper.convertValue(obj, BeneficarioListDto.class))
                .collect(Collectors.toList());
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
