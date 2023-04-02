package com.tortones.APItortones.model;

public class SesionUsuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String rol;

    public SesionUsuario(Long id, String rol, String nombre, String apellido) {
        this.id = id;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String correo) {
        this.rol = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
