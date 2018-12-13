package com.refugios.equipo.refugiosproyect.refugios.comentarios;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Mensaje;

public class ComentarioRecibir extends Mensaje {

    //ATRIBUTOS
    private long hora;

    //CONSTRUCTORES

    public ComentarioRecibir() {
    }

    public ComentarioRecibir(long hora) {
        this.hora = hora;
    }

    public ComentarioRecibir(String mensaje, String correo, String nombre, String type_mensaje, long hora) {
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
