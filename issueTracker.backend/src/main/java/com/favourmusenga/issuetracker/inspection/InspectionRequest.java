package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.status.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class InspectionRequest {

    private String Comment;

    @NotBlank
    private String date;

    @NotBlank
    private String userEmail;
    @NotNull
    private Long statusId;

    @NotNull
    private Long equipmentId;

    public InspectionRequest(String comment, String date, String userEmail, Long statusId, Long equipmentId) {
        Comment = comment;
        this.date = date;
        this.userEmail = userEmail;
        this.statusId = statusId;
        this.equipmentId = equipmentId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public String toString() {
        return "InspectRequest{" +
                "Comment='" + Comment + '\'' +
                ", date='" + date + '\'' +
                ", appUser=" + userEmail +
                ", statusId=" + statusId +
                ", equipmentId=" + equipmentId +
                '}';
    }
}
