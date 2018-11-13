package com.example.equipo.refugiosproyect.clasesPrincipales;

public class BBDD {
    private static final String ip = "www.iesmurgi.org";
    private static final String bd = "/DanielRueda";
    private static final String usuario = "DanielRueda";
    private static final String clave = "D1N32LR";

    public static String getIp() {
        return ip;
    }

    public static String getBd() {
        return bd;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getClave() {
        return clave;
    }
}
