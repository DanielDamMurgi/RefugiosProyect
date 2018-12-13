package com.refugios.equipo.refugiosproyect.weather.model;

public class Wind {

    //ATRIBUTOS
    private double speed;
    private double deg;

    //CONSTRUCTOR
    public Wind() {
    }

    //GETTERS Y SETTERS
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return (int) deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
