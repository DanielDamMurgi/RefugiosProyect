package com.example.equipo.refugiosproyect.ClasesPrincipales;

public class Ruta {
    //ATRIBUTOS--------------------------------------------------------------
    private String id;
    private String nombre;
    private int imagen;
    private int kml;

    //CONSTRUCTORES-----------------------------------------------------------

    public Ruta() {
    }

    public Ruta(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Ruta(String id, String nombre, int imagen, int kml) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.kml = kml;
    }
    //GETTERS Y SETTERS-----------------------------------------------------------

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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getKml() {
        return kml;
    }

    public void setKml(int kml) {
        this.kml = kml;
    }
}
