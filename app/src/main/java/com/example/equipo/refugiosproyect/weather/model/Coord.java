package com.example.equipo.refugiosproyect.weather.model;

public class Coord {

    //ATRIBUTOS
    private double lon;
    private double lat;

    //CONSTRUCTOR

    public Coord() {
    }

    //GETTERS Y SETTERS

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString(){
        return new StringBuilder("[").append(this.lat).append(',').append(this.lon).append(']').toString();
    }
}
