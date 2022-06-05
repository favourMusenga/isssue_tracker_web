package com.favourmusenga.issuetracker.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class AppUserController {
    private final AppUserService userService;

    @GetMapping
    List<AppUser> getAllUser(){
        return userService.getAllUser();
    }
}
