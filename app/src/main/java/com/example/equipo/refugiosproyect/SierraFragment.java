package com.example.equipo.refugiosproyect;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SierraFragment extends Fragment implements View.OnClickListener{

    private CardView infoCard;

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


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent i;


        switch (v.getId()){
            case R.id.cardView_infoSierra:
                Toast.makeText(getActivity(),"llega",Toast.LENGTH_SHORT).show();
                i = new Intent(getActivity(),InfoActivity.class);
                startActivity(i);
                break;
        }
    }
}
