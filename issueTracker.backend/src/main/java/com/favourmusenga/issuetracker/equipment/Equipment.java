package com.favourmusenga.issuetracker.equipment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.favourmusenga.issuetracker.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Location location;

    public Equipment(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }
}
