package com.jaeun.dantong.domain.entity;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    LEADER("LEADER")
    ;

    final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String role() { return role; }
}
