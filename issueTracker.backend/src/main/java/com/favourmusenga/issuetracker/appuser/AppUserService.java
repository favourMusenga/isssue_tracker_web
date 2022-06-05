package com.favourmusenga.issuetracker.appuser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppUserService implements IAppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser == null){
            throw new UsernameNotFoundException("email not found");
        }

        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();

        authority.add(new SimpleGrantedAuthority(appUser.getRole().getName()));

        return new User(appUser.getEmail(), appUser.getPassword(), authority);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser newUser = appUserRepository.save(appUser);
        return newUser;
    }

    @Override
    public AppUser getUser(String email) {
        AppUser user = appUserRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<AppUser> getAllUser() {
        return appUserRepository.findAll();
    }
}
