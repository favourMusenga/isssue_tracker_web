package com.favourmusenga.issuetracker.equipment;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EquipmentRequest {

    @NotNull(message="name is required")
    @NotBlank(message="name is required")
    private String name;

    private String description;

    @NotNull(message="id is required")
    private Long locationId;

    public EquipmentRequest(String name, String description, Long locationId) {
        this.name = name;
        this.description = description;
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "EquipmentRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", locationId=" + locationId +
                '}';
    }
}
