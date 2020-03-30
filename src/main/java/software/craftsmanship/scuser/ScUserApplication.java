package software.craftsmanship.scuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.entities.User;
import software.craftsmanship.scuser.enums.Gender;
import software.craftsmanship.scuser.enums.Status;
import software.craftsmanship.scuser.repositories.SpaceRepository;
import software.craftsmanship.scuser.repositories.UserRepository;

@SpringBootApplication
public class ScUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScUserApplication.class, args);
	}

	@Bean
	CommandLineRunner start(final UserRepository userRepository, final SpaceRepository spaceRepository){
		return arg -> {
			final Space space = new Space(null, "john.doe@gmail.com_space", null);
			spaceRepository.save(space);
			final User user = new User(null, "john.doe@gmail.com", "john.doe@gmail.com", Gender.Mister, "John", "Doe", Status.ACTIVE, space);
			userRepository.save(user);

				System.out.println("ScUserApplication start");
		};

	}

}