package com.example.equipo.refugiosproyect.refugios.comentarios;

import com.example.equipo.refugiosproyect.clasesPrincipales.Mensaje;

public class MensajeRecibir extends Mensaje {

    //ATRIBUTOS
    private long hora;

    //CONSTRUCTORES

    public MensajeRecibir() {
    }

    public MensajeRecibir(long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensaje, String correo, String nombre, String type_mensaje, long hora) {
        super(mensaje, correo, nombre, type_mensaje);
        this.hora = hora;
    }

    //GETTERS Y SETTERS

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }
}
