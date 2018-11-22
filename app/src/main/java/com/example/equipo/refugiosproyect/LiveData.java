package com.example.equipo.refugiosproyect;

import android.arch.lifecycle.ViewModel;

import com.example.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.clasesPrincipales.Ruta;
import com.example.equipo.refugiosproyect.clasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.clasesPrincipales.Usuario;

import java.util.ArrayList;

public class LiveData extends ViewModel {

    private static ArrayList<Sierra> sierras = new ArrayList<>();
    private static ArrayList<Refugio> refugios = new ArrayList<>();
    private static ArrayList<Ruta> rutas = new ArrayList<>();

    public static ArrayList<Sierra> getSierras() {
        return sierras;
    }

    public static void setSierras(ArrayList<Sierra> sierras) {
        LiveData.sierras = sierras;
    }

    public static ArrayList<Refugio> getRefugios() {
        return refugios;
    }

    public static void setRefugios(ArrayList<Refugio> refugios) {
        LiveData.refugios = refugios;
    }

    public static ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public static void setRutas(ArrayList<Ruta> rutas) {
        LiveData.rutas = rutas;
    }
}
