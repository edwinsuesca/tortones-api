package com.tortones.APItortones.model;

public class Cotizacion {

    private String asunto;

    private String remitente;

    private  String mensaje;

    public Cotizacion(String asunto, String remitente, String mensaje) {
        this.asunto = asunto;
        this.remitente = remitente;
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
