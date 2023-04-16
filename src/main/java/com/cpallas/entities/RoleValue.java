package com.cpallas.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleValue {

    USER("user"),
    ADMIN("admin");

    private final String role;
}
