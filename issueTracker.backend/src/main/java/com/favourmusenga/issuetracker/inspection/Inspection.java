package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.user.User;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String Comment;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Status status;

    @ManyToOne
    @JoinColumn(
            name = "equipment_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Equipment equipment;

    public Inspection(String comment, String date, User user, Status status, Equipment equipment) {
        Comment = comment;
        this.date = date;
        this.user = user;
        this.status = status;
        this.equipment = equipment;
    }
}
