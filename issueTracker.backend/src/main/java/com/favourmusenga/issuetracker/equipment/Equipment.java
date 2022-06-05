package com.favourmusenga.issuetracker.equipment;

import com.favourmusenga.issuetracker.location.Location;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
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
