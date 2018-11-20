package com.example.equipo.refugiosproyect.sierras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.equipo.refugiosproyect.clasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.refugios.RefugioPanelActivity;
import com.example.equipo.refugiosproyect.weather.WeatherActivity;
import com.squareup.picasso.Picasso;

public class SierraFragment extends Fragment implements View.OnClickListener {

    //ATRIBUTOS
    private CardView infoCard, refugioCard, fotosCard, climaCard;
    private Sierra sierra;
    private ImageView imagen;

    //IMPLEMENTACION
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

        climaCard = view.findViewById(R.id.cardView_climaSierra);
        climaCard.setOnClickListener(this);

        sierra = (Sierra) getActivity().getIntent().getExtras().getSerializable("sierra");

        imagen = view.findViewById(R.id.iv_sierraPanel);

        Picasso.with(getActivity()).load(sierra.getFoto().trim()).into(imagen);

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
                i = new Intent(getActivity(),RefugioPanelActivity.class);
                i.putExtra("sierra",sierra);
                startActivity(i);
                break;
            case R.id.cardView_fotosSierras:
                i = new Intent(getActivity(), FotosSierraActivity.class);
                i.putExtra("idSierra",sierra.getId());
                startActivity(i);
                break;
            case R.id.cardView_climaSierra:
                i = new Intent(getActivity(), WeatherActivity.class);
                i.putExtra("locat",true);
                i.putExtra("latitudSierra", sierra.getLatutud());
                i.putExtra("longitudSierra", sierra.getLongitud());
                i.putExtra("nombreSierra", sierra.getNombre());
                startActivity(i);
                break;
            default:

                break;
        }

    }

}
