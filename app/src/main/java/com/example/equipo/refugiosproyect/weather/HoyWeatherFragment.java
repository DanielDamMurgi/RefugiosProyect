package com.example.equipo.refugiosproyect.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.R;

public class HoyWeatherFragment extends Fragment {

    //ATRIBUTOS
    static HoyWeatherFragment instancia;

    //GETTERS Y SETTERS
    public static HoyWeatherFragment getInstancia() {
        if (instancia == null){
            instancia = new HoyWeatherFragment();
        }
        return instancia;
    }

    //IMPLEMENTACION

    public HoyWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoy_weather, container, false);
    }

}
