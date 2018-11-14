package com.example.equipo.refugiosproyect;

import android.arch.lifecycle.ViewModel;

import com.example.equipo.refugiosproyect.clasesPrincipales.Sierra;

import java.util.ArrayList;

public class LiveData extends ViewModel {

    private static ArrayList<Sierra> sierras = new ArrayList<>();

    public static ArrayList<Sierra> getSierras() {
        return sierras;
    }

    public static void setSierras(ArrayList<Sierra> sierras) {
        LiveData.sierras = sierras;
    }
}
