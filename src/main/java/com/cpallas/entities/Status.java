package com.cpallas.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    ACTIVE("active"),
    BLOCKED("blocked"),
    CANCELED("canceled");

    private final String status;
}
