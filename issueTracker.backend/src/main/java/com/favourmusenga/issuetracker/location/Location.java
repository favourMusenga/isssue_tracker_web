package com.favourmusenga.issuetracker.location;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @NotNull
    @Column(nullable = false)
    private String physicalAddress;

    @NotNull
    @Column(nullable = false)
    private Integer zone;

    @NotNull
    @Column(nullable = false, length = 50)
    private String town;

    public Location(String physicalAddress, Integer zone, String town) {
        this.physicalAddress = physicalAddress;
        this.zone = zone;
        this.town = town;
    }
}
