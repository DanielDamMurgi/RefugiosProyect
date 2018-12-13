package com.refugios.equipo.refugiosproyect.refugios;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.refugios.equipo.refugiosproyect.R;

public class InfoRefugioFragment extends Fragment {

    private static String info;
    private TextView textView;

    public InfoRefugioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_refugio, container, false);

        textView = view.findViewById(R.id.tv_infoRefugio);

        cargarTexto(info);


        return view;
    }

    private void cargarTexto(String texto) {

        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(texto);
    }

    public static void setInfo(String info){
        InfoRefugioFragment.info = info;
    }

}
