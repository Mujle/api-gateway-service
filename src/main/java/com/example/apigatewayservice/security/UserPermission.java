package com.example.apigatewayservice.security;

public enum  UserPermission {
    MAKE_ORDERS("make:orders"),
    SEE_ORDERS("see:orders"),
    ADD_STOCK("add:stock"),
    SEE_CHANGES("see:changes"),
    SEE_STOCK("see:stock");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
