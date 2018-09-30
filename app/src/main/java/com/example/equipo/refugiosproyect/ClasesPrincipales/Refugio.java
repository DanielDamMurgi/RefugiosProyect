package com.example.equipo.refugiosproyect.ClasesPrincipales;

public class Refugio {

    //ATRIBUTOS-------------------------------------------------------------------

    private String id;
    private String nombre;
    private String informacion;
    private String rutas;
    private int altitud;
    private String situacion;
    private int foto;

    //CONSTRUCTORES--------------------------------------------------------------

    public Refugio() {
    }

    public Refugio(String nombre, int altitud, String situacion, int foto) {
        this.nombre = nombre;
        this.altitud = altitud;
        this.situacion = situacion;
        this.foto = foto;
    }

    //GETTERS Y SETTERS----------------------------------------------------------

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

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getRutas() {
        return rutas;
    }

    public void setRutas(String rutas) {
        this.rutas = rutas;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
