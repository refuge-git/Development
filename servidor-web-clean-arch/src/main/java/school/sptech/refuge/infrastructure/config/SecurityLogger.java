package school.sptech.refuge.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SecurityLogger {
    private static final Logger logger = LoggerFactory.getLogger(SecurityLogger.class);

    public void logSecurityEvent(String action , String local, String resource) {
        logger.info("Ação: {} | Local {} | Recurso: {}", action, local, resource);
    }

    public void logSecurityLogin(String action, String local, String user){
        logger.warn("Ação: {} | Local: {} | Usuário: {}", action, local, user);
    }
}
