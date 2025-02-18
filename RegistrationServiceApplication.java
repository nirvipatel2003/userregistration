package io.bytesbank.registration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class RegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication myapp = new SpringApplication(RegistrationServiceApplication.class);
		ApplicationContext context = myapp.run(args);
		log.info("Application started - {}",context.getEnvironment().getActiveProfiles());
	}

}
