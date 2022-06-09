package com.favourmusenga.issuetracker.location;

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
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "physical address is required")
    @NotBlank(message = "physical address is required")
    @Column(nullable = false)
    private String physicalAddress;

    @NotNull(message = "zone is required")
    @NotBlank(message = "zone is required")
    @Column(nullable = false)
    private Integer zone;

    @NotNull(message = "town is required")
    @NotBlank(message = "town is required")
    @Column(nullable = false, length = 50)
    private String town;

    public Location(String physicalAddress, Integer zone, String town) {
        this.physicalAddress = physicalAddress;
        this.zone = zone;
        this.town = town;
    }
}
