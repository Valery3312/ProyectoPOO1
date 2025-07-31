package model;

public enum Rol {
    ADMIN,
    CAJERO;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static Rol fromString(String rol) {
        return Rol.valueOf(rol.toUpperCase());
    }
}
