package com.favourmusenga.issuetracker.inspection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.appuser.AppUser;
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
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String Comment;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "status_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "equipment_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Equipment equipment;

    public Inspection(String comment, String date, AppUser appUser, Status status, Equipment equipment) {
        Comment = comment;
        this.date = date;
        this.appUser = appUser;
        this.status = status;
        this.equipment = equipment;
    }
}
