package com.example.equipo.refugiosproyect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Refugio;

import java.util.ArrayList;

public class RefugioActivity extends AppCompatActivity {

    //ATRIBUTOS
    private RecyclerView mRecyclerView_refugios;
    private RecyclerView.LayoutManager mLayoutManager_refugios;

    private ArrayList<Refugio> refugios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugio);

        cargar();
        mRecyclerView_refugios = findViewById(R.id.recyclerView_refugio);

        mLayoutManager_refugios = new LinearLayoutManager(this);
        mRecyclerView_refugios.setLayoutManager(mLayoutManager_refugios);

        lanzarAdapter();
    }

    public void lanzarAdapter() {
        RefugioAdapter adapter = new RefugioAdapter(this, refugios);
        mRecyclerView_refugios.setLayoutManager(mLayoutManager_refugios);
        mRecyclerView_refugios.setAdapter(adapter);
    }

    private void cargar(){
        refugios.add(new Refugio("La cucaracha",2000,"Cerrado",R.drawable.refugio_polarda));
        refugios.add(new Refugio("La polarda",2500,"Abierto",R.drawable.refugio_polarda));
    }
}
