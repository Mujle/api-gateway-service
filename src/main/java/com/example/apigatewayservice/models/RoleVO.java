package com.example.apigatewayservice.models;


import java.util.Objects;

public class RoleVO {

    private String role;

    public RoleVO() {
    }

    public RoleVO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleVO roleVO = (RoleVO) o;
        return Objects.equals(role, roleVO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}