package com.favourmusenga.issuetracker.appuser;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class AppUserRequest {
    @NotNull(message = "first name is required")
    @NotBlank(message = "first name is required")
    private String firstName;

    @NotNull(message = "last name is required")
    @NotBlank(message = "last name is required")
    private String lastName;

    private String middleName;

    @NotNull(message = "email is required")
    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "password is required")
    @NotNull(message = "password is required")
    private String password;

    @NotBlank(message = "status is required")
    @NotNull(message = "status is required")
    private String status;
}
