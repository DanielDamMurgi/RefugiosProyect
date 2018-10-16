package com.example.equipo.refugiosproyect.refugios;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Ruta;
import com.example.equipo.refugiosproyect.R;

import java.util.ArrayList;

public class RutasFragment extends Fragment {

    private ArrayList<Ruta> rutas;
    private RecyclerView rv_rutas;
    private RecyclerView.LayoutManager lm_rutas;

    public RutasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutas, container, false);

        datos();
        rv_rutas = view.findViewById(R.id.rv_rutas);
        lm_rutas = new LinearLayoutManager(getActivity());

        lanzarAdapter();

        return view;
    }

    public void lanzarAdapter() {
        RutasAdapter adapter = new RutasAdapter(getActivity(), rutas);
        rv_rutas.setLayoutManager(lm_rutas);
        rv_rutas.setAdapter(adapter);
    }

    private void datos(){
        rutas = new ArrayList<>();
        rutas.add(new Ruta("1","Abrucena - Ubeire",R.drawable.sierra_nevada,R.raw.sierra_evada));
        rutas.add(new Ruta("1","Abrucena - Ubeire",R.drawable.sierra_nevada,R.raw.sierra_evada));
    }

}
