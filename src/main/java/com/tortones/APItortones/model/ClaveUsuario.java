package com.tortones.APItortones.model;

public class ClaveUsuario {

    private String correo;
    private String claveActual;
    private String claveNueva;

    public ClaveUsuario(String correo, String claveActual, String claveNueva) {
        this.correo = correo;
        this.claveActual = claveActual;
        this.claveNueva = claveNueva;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }
}
