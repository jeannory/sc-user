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
			final Space space1 = new Space(null, "user1_space", null);
			final Space space2 = new Space(null, "user2_space", null);
			final Space space3 = new Space(null, "user3_space", null);
			final Space space4 = new Space(null, "user4_space", null);
			final Space space5 = new Space(null, "user5_space", null);
			final Space space6 = new Space(null, "user6_space", null);
			final Space space7 = new Space(null, "user7_space", null);
			final Space space8 = new Space(null, "user8_space", null);
			final Space space9 = new Space(null, "user9_space", null);
			final Space space10 = new Space(null, "user10_space", null);
			final Space space11 = new Space(null, "john.doe@gmail.com_space", null);
			final Space space12 = new Space(null, "test_space", null);
			spaceRepository.save(space1);
			spaceRepository.save(space2);
			spaceRepository.save(space3);
			spaceRepository.save(space4);
			spaceRepository.save(space5);
			spaceRepository.save(space6);
			spaceRepository.save(space7);
			spaceRepository.save(space8);
			spaceRepository.save(space9);
			spaceRepository.save(space10);
			spaceRepository.save(space11);
			spaceRepository.save(space12);
			final User user1 = new User(null, "user1", "user1@gmail.com", Gender.Mister, "firstName1", "name1", Status.ACTIVE, space1);
			final User user2 = new User(null, "user2", "user2@gmail.com", Gender.Madam, "firstName2", "name2", Status.ACTIVE, space2);
			final User user3 = new User(null, "user3", "user3@gmail.com", Gender.Mister, "firstName3", "name3", Status.ACTIVE, space3);
			final User user4 = new User(null, "user4", "user4@gmail.com", Gender.Madam, "firstName4", "name4", Status.ACTIVE, space4);
			final User user5 = new User(null, "user5", "user5@gmail.com", Gender.Mister, "firstName5", "name5", Status.ACTIVE, space5);
			final User user6 = new User(null, "user6", "user6@gmail.com", Gender.Madam, "firstName6", "name6", Status.INACTIVE, space6);
			final User user7 = new User(null, "user7", "user7@gmail.com", Gender.Mister, "firstName7", "name7", Status.INACTIVE, space7);
			final User user8 = new User(null, "user8", "user8@gmail.com", Gender.Madam, "firstName8", "name8", Status.ACTIVE, space8);
			final User user9 = new User(null, "user9", "user9@gmail.com", Gender.Mister, "firstName9", "name9", Status.ACTIVE, space9);
			final User user10 = new User(null, "user10", "user10@gmail.com", Gender.Madam, "firstName10", "name10", Status.ACTIVE, space10);
			final User user11 = new User(null, "john.doe@gmail.com", "john.doe@gmail.com", Gender.Mister, "John", "Doe", Status.ACTIVE, space11);
			final User user12 = new User(null, "test", "test@gmail.com", Gender.Mister, "testa", "testy", Status.ACTIVE, space12);
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);
			userRepository.save(user6);
			userRepository.save(user7);
			userRepository.save(user8);
			userRepository.save(user9);
			userRepository.save(user10);
			userRepository.save(user11);
			userRepository.save(user12);
				System.out.println("ScUserApplication start");
		};

	}

}