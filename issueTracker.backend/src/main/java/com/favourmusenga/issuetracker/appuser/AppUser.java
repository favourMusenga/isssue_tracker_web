package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.role.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "username", nullable = false)
    private UserName userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public AppUser(String email, String password, UserName userName, Role statusID) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = statusID;
    }
}

