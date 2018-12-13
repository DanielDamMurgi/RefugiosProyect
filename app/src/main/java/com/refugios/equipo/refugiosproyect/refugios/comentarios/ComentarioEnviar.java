package com.refugios.equipo.refugiosproyect.refugios.comentarios;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Mensaje;

import java.util.Map;

public class ComentarioEnviar extends Mensaje {

    //ATRIBUTOS
    private Map hora;

    //CONSTRUCTORES
    public ComentarioEnviar() {
    }

    public ComentarioEnviar(Map hora) {
        this.hora = hora;
    }

    public ComentarioEnviar(String mensaje, String correo, String nombre, String type_mensaje, Map hora) {
        super(mensaje, correo, nombre, type_mensaje);
        this.hora = hora;
    }

    //GETTERS Y SETTERS

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
