package com.example.equipo.refugiosproyect.refugios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.R;

import java.util.ArrayList;

public class RefugioActivity extends AppCompatActivity {

    //ATRIBUTOS
    private RecyclerView mRecyclerView_refugios;
    private RecyclerView.LayoutManager mLayoutManager_refugios;
    private int idSierra;

    private ArrayList<Refugio> refugios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugio);

        mRecyclerView_refugios = findViewById(R.id.recyclerView_refugio);

        mLayoutManager_refugios = new LinearLayoutManager(this);

        refugios = (ArrayList<Refugio>) getIntent().getSerializableExtra("refugios");

        lanzarAdapter();

    }

    public void lanzarAdapter() {
        RefugioAdapter adapter = new RefugioAdapter(this, refugios);
        mRecyclerView_refugios.setLayoutManager(mLayoutManager_refugios);
        mRecyclerView_refugios.setAdapter(adapter);
    }

}
