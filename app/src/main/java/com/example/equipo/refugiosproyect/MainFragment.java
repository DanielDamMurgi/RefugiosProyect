package com.example.equipo.refugiosproyect;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Sierra;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    //ATRIBUTOS
    private RecyclerView mRecyclerView_sierras;
    private RecyclerView.LayoutManager mLayoutManager_sierras;

    private ArrayList<Sierra> sierras = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        cargar();
        mRecyclerView_sierras = view.findViewById(R.id.recyclerView_sierras);

        mLayoutManager_sierras = new LinearLayoutManager(view.getContext());
        mRecyclerView_sierras.setLayoutManager(mLayoutManager_sierras);

        lanzarAdapter();

        return view;
    }

    public void lanzarAdapter() {
        MainAdapter adapter = new MainAdapter(getActivity(), sierras);
        mRecyclerView_sierras.setLayoutManager(mLayoutManager_sierras);
        mRecyclerView_sierras.setAdapter(adapter);
    }

    private void cargar(){
        sierras.add(new Sierra("Sierra nevada",R.drawable.sierra_nevada));
        sierras.add(new Sierra("Sierra no nevada",R.drawable.sierra_nevada));
        sierras.add(new Sierra("Sierra nevada",R.drawable.sierra_nevada));
        sierras.add(new Sierra("Sierra no nevada",R.drawable.sierra_nevada));
    }

}
