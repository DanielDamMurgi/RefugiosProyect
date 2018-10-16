package com.example.equipo.refugiosproyect.sierras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Imagen;
import com.example.equipo.refugiosproyect.ImagenAdapter;
import com.example.equipo.refugiosproyect.R;

public class FotosSierraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_sierra);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.rv_imagenesSierra);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImagenAdapter adapter = new ImagenAdapter(this, Imagen.getImagenes());
        recyclerView.setAdapter(adapter);
    }
}
