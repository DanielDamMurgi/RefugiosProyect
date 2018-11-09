package com.example.equipo.refugiosproyect.refugios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.equipo.refugiosproyect.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentariosFragment extends Fragment {

    //ATRIBUTOS
    private RecyclerView recyclerView_mensaje;
    private EditText txt_mensaje;
    private Button btn_enviar;


    public ComentariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comentarios, container, false);

        recyclerView_mensaje = view.findViewById(R.id.recycler_chat);
        txt_mensaje = view.findViewById(R.id.txt_chat);
        btn_enviar = view.findViewById(R.id.bt_chat);

        return view;
    }

}
