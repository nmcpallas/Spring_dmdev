package com.cpallas.entities;

public enum Status {

    ACTIVE("active"),
    BLOCKED("blocked"),
    CANCELED("canceled");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getCode() {
        return status;
    }
}
