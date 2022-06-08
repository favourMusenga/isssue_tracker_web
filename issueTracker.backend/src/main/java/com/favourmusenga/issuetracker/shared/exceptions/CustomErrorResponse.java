package com.favourmusenga.issuetracker.shared.exceptions;

public class CustomErrorResponse <T>{

    private int status;
    private T error;

    private String statusName;

    public CustomErrorResponse(int status, T error, String statusName) {
        this.status = status;
        this.error = error;
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "CustomErrorResponse{" +
                "status=" + status +
                ", error=" + error.toString() +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
