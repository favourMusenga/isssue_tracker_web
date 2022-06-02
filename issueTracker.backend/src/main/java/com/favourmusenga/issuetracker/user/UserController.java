package com.favourmusenga.issuetracker.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/user")
public class UserController {
    private final UserService userService;
}
