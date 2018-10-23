package com.example.equipo.refugiosproyect;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.equipo.refugiosproyect.ClasesPrincipales.Usuario;

import java.util.ArrayList;

public class PanelActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Usuario> usuario;
    private TextView tv_Nombre;
    private CardView cv_cambiarClave,cv_cambiarNombre,cv_cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        tv_Nombre = findViewById(R.id.tvNombreUsu);

        usuario = (ArrayList<Usuario>) getIntent().getSerializableExtra("usuarios");

        cv_cambiarClave = findViewById(R.id.cv_cambiarClave);
        cv_cambiarNombre = findViewById(R.id.cv_cambiarNombre);
        cv_cerrarSesion = findViewById(R.id.cv_cerrarSesion);

        cv_cambiarClave.setOnClickListener(this);
        cv_cambiarNombre.setOnClickListener(this);
        cv_cerrarSesion.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_cambiarClave:
                Intent intent = new Intent(getApplicationContext(),CambiarClaveActivity.class);

                startActivity(intent);
                break;

            case R.id.cv_cambiarNombre:

                break;

            case R.id.cv_cerrarSesion:

                break;

            default:
                break;
        }
    }
}
