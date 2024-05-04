package br.com.jjdev.APIREST.domain.user;

public enum Roles {
    ADMIN("admin"),
    COSTUMER("costumer");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
