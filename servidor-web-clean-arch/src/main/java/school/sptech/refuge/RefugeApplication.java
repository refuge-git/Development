package school.sptech.refuge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RefugeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefugeApplication.class, args);
	}
}
