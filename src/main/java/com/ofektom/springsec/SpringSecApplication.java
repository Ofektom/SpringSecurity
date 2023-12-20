package com.ofektom.springsec;

import com.ofektom.springsec.entities.Users;
import com.ofektom.springsec.enums.Role;
import com.ofektom.springsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecApplication.class, args);
	}

	public void run(String... args){
		Users adminAccount = userRepository.findByRole(Role.ADMIN);
		if (null == adminAccount){
			Users users = new Users();
			users.setEmail("admin@gmail.com");
			users.setFirstname("admin");
			users.setLastname("admin");
			users.setRole(Role.ADMIN);
			users.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(users);
		}
	}

}
