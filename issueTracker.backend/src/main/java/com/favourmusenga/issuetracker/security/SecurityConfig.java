package com.favourmusenga.issuetracker.security;

import com.favourmusenga.issuetracker.security.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalAuthentication
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationManager config(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/api/location").permitAll()
                .antMatchers(HttpMethod.POST,"/api/role").permitAll()
                .antMatchers(HttpMethod.GET,"/api/role").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .anyRequest().authenticated();
        http.apply(MyCustomDsl.myCustomDsl());

        return http.build();
    }


}
