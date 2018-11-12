package com.example.equipo.refugiosproyect.clasesPrincipales;

public class Mensaje {

    //ATRIBUTOS
    private String mensaje;
    private String urlFoto;
    private String correo;
    private String nombre;
    private String type_mensaje;
    private String hora;

    //CONSTRUCTOR
    public Mensaje() {
    }

    public Mensaje(String mensaje, String correo, String nombre, String type_mensaje, String hora) {
        this.mensaje = mensaje;
        this.correo = correo;
        this.nombre = nombre;
        this.type_mensaje = type_mensaje;
        this.hora = hora;
    }

    public Mensaje(String mensaje, String urlFoto, String correo, String nombre, String type_mensaje, String hora) {
        this.mensaje = mensaje;
        this.urlFoto = urlFoto;
        this.correo = correo;
        this.nombre = nombre;
        this.type_mensaje = type_mensaje;
        this.hora = hora;
    }

    //GETTERS Y SETTERS
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getType_mensaje() {
        return type_mensaje;
    }

    public void setType_mensaje(String type_mensaje) {
        this.type_mensaje = type_mensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
