package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
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
    public void saveUser(AppUser appUser) throws CustomBadRequestException {
        AppUser emailExists = appUserRepository.findByEmail(appUser.getEmail());
        if (emailExists != null){
            throw new CustomBadRequestException("Email already exist");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser getUserByEmail(String email) throws CustomNotFoundException {
        AppUser user = appUserRepository.findByEmail(email);

        if(user == null){
            throw new CustomNotFoundException("user does not exist");
        }
        return user;
    }

    @Override
    public List<AppUser> getAllUser() {
        return appUserRepository.findAll();
    }
}
