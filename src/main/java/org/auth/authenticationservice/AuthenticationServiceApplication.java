package org.auth.authenticationservice;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.configs.JwtTokenParams;
import org.auth.authenticationservice.configs.RsaKeyConfig;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.RoleRequestDTO;
import org.auth.authenticationservice.dtos.UserRequestDTO;
import org.auth.authenticationservice.dtos.UserResponseDTO;
import org.auth.authenticationservice.enums.Gender;
import org.auth.authenticationservice.services.RoleService;
import org.auth.authenticationservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
@EnableConfigurationProperties({
	RsaKeyConfig.class,
	JwtTokenParams.class,
})
@AllArgsConstructor
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService, RoleService roleService){
		return args -> {
			String secret = generateRandomSecret();
			roleService.createRole( new RoleRequestDTO(null,"USER"));
			roleService.createRole(new RoleRequestDTO(null,"ADMIN"));
			ApiResponseDTO<UserResponseDTO> user = userService.createUser(
					new UserRequestDTO(
							"admin",
							"foo",
							"bar",
							"admin@admin.com",
							secret,
							secret,
							Gender.MALE,
							null,
							null,
							null,
							null
					)
			);
			System.out.println();
			System.out.println();
			System.out.println("                "+ "user : " + user.getData().getUsername() );
			System.out.println("                "+ "password : " + secret );
			System.out.println();
			System.out.println();
		};
	}

	private static String generateRandomSecret() {
		byte[] randomBytes = new byte[32];
		new SecureRandom().nextBytes(randomBytes);
		return Base64.getEncoder().encodeToString(randomBytes);
	}

}
