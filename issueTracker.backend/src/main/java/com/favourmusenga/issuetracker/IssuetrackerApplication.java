package com.favourmusenga.issuetracker;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.AppUserService;
import com.favourmusenga.issuetracker.appuser.UserName;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleRepository;
import com.favourmusenga.issuetracker.role.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IssuetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuetrackerApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
