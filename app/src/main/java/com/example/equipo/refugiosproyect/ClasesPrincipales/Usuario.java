package com.example.equipo.refugiosproyect.ClasesPrincipales;

public class Usuario {
    //ATRIBUTOS-----------------------------------------------------------
    private String id;
    private String email;
    private String nombre;
    private String clave;

    //CONSTRUCTORES-------------------------------------------------------
    public Usuario() {
    }

    public Usuario(String id, String nombre, String email, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }

    //GETTERS Y SETTERS------------------------------------------------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
