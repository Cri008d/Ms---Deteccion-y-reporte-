package com.example.demo.model;

public class AlertaMasivaDTO {
    private String asunto;
    private String mensaje;

    // Constructores
    public AlertaMasivaDTO() {}

    public AlertaMasivaDTO(String asunto, String mensaje) {
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    // Getters y Setters
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
