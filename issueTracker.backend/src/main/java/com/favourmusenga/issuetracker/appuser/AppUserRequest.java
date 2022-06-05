package com.favourmusenga.issuetracker.appuser;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppUserRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String status;
}
