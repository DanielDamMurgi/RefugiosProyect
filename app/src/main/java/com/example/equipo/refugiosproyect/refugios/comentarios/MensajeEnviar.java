package com.example.equipo.refugiosproyect.refugios.comentarios;

import com.example.equipo.refugiosproyect.clasesPrincipales.Mensaje;

import java.util.Map;

public class MensajeEnviar extends Mensaje {

    //ATRIBUTOS
    private Map hora;

    //CONSTRUCTORES
    public MensajeEnviar() {
    }

    public MensajeEnviar(Map hora) {
        this.hora = hora;
    }

    public MensajeEnviar(String mensaje, String correo, String nombre, String type_mensaje, Map hora) {
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
