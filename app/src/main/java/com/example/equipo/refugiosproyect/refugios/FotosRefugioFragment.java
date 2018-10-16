package com.example.equipo.refugiosproyect.refugios;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Imagen;
import com.example.equipo.refugiosproyect.ImagenAdapter;
import com.example.equipo.refugiosproyect.R;

public class FotosRefugioFragment extends Fragment {

    public FotosRefugioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fotos_refugio, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        RecyclerView recyclerView = view.findViewById(R.id.rv_fotosRefugios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImagenAdapter adapter = new ImagenAdapter(getContext(), Imagen.getImagenes());
        recyclerView.setAdapter(adapter);

        return  view;
    }
}
