package school.sptech.refuge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SecurityLogger {
    private static final Logger logger = LoggerFactory.getLogger(SecurityLogger.class);

    public void logSecurityEvent(LocalDateTime data, String action , String local, String resource) {
        logger.info("Horário no servidor: {} | Ação: {} | Local {} | Recurso: {}", data, action, local, resource);
    }
}
