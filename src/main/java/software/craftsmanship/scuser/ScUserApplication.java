package software.craftsmanship.scuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScUserApplication.class, args);
	}

	@Bean
	CommandLineRunner start(){
		return arg -> {
				System.out.println("ScUserApplication start");
		};

	}

}
