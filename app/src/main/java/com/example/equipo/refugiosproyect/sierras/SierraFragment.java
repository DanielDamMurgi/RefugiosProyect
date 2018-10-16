package com.example.equipo.refugiosproyect.sierras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.refugios.RefugioActivity;

public class SierraFragment extends Fragment implements View.OnClickListener{

    private CardView infoCard, refugioCard, fotosCard, mapaCard;
    private Sierra sierra;

    public SierraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sierra, container, false);

        infoCard = view.findViewById(R.id.cardView_infoSierra);
        infoCard.setOnClickListener(this);

        refugioCard = view.findViewById(R.id.cardView_refugioSierra);
        refugioCard.setOnClickListener(this);

        fotosCard = view.findViewById(R.id.cardView_fotosSierras);
        fotosCard.setOnClickListener(this);

        mapaCard = view.findViewById(R.id.cardView_mapaSierra);
        mapaCard.setOnClickListener(this);

        sierra = (Sierra) getActivity().getIntent().getExtras().getSerializable("sierra");


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.cardView_infoSierra:
                i = new Intent(getActivity(),InfoActivity.class);
                i.putExtra("infoSierra",sierra.getInfo());
                i.putExtra("nombreSierra",sierra.getNombre());
                i.putExtra("fotoSierra",sierra.getFoto());
                startActivity(i);
                break;

            case R.id.cardView_refugioSierra:
                i = new Intent(getActivity(),RefugioActivity.class);
                startActivity(i);
                break;

            case R.id.cardView_fotosSierras:
                i = new Intent(getActivity(), FotosSierraActivity.class);
                startActivity(i);
                break;

            case R.id.cardView_mapaSierra:
                i = new Intent(getActivity(),MapsActivity.class);
                startActivity(i);
                break;
            default:

                break;
        }

    }
}
