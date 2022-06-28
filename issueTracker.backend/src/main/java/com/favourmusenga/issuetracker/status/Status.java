package com.favourmusenga.issuetracker.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "message is required")
    @NotBlank(message = "message is required")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;


    public Status(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
