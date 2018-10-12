package com.example.equipo.refugiosproyect.ClasesPrincipales;

public class Sierra {
    //ATRIBUTOS----------------------------------------------------
    private String id;
    private String nombre;
    private String info;
    private int foto;
    private String latutud;
    private String longitud;

    //CONSTRUCTORES------------------------------------------------
    public Sierra() {

    }

    public Sierra(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public Sierra(String id, String nombre, String info, int foto) {
        this.id = id;
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
    }

    //GETTERS Y SETTERS---------------------------------------------
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getLatutud() {
        return latutud;
    }

    public void setLatutud(String latutud) {
        this.latutud = latutud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
