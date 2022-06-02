package com.favourmusenga.issuetracker.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String physicalAddress;

    @Column(nullable = false)
    private Integer zone;

    @Column(nullable = false)
    private String town;

    public Location(String physicalAddress, Integer zone, String town) {
        this.physicalAddress = physicalAddress;
        this.zone = zone;
        this.town = town;
    }
}
