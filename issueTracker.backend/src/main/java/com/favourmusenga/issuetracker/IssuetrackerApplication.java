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

	@Bean
	public CommandLineRunner run(AppUserService appUserService, RoleService roleService){
		return args ->{
			Role inspectorRole = roleService.saveNewRole(new Role("Inspector"));
			Role supervisorRole = roleService.saveNewRole(new Role("Supervisor"));

			appUserService.saveUser(new AppUser("favour@gmail.com","test1234",new UserName("favour",null , "musenga"),supervisorRole));
			appUserService.saveUser(new AppUser("mary@gmail.com","test1234",new UserName("mary",  "chanda","banda"),inspectorRole));
			appUserService.saveUser(new AppUser("jane@gmail.com","test1234",new UserName("favour", null,"doe"),supervisorRole));
			appUserService.saveUser(new AppUser("john@gmail.com","test1234",new UserName("favour", null,"doe"),inspectorRole));
		};
	}
}
