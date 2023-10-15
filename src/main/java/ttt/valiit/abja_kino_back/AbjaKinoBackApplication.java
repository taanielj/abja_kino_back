package ttt.valiit.abja_kino_back;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ttt.valiit.abja_kino_back.business.user.UserService;

@SpringBootApplication
public class AbjaKinoBackApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(AbjaKinoBackApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAdmin(UserService userService) {
		return args -> userService.initAdminUser();
	}


}
