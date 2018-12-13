package com.refugios.equipo.refugiosproyect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Usuario;

import java.util.ArrayList;

public class PanelActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Usuario> usuario;
    private TextView tv_Nombre;
    private CardView cv_cambiarClave,cv_cambiarNombre,cv_cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        //tv_Nombre = findViewById(R.id.tvNombreUsu);

        usuario = (ArrayList<Usuario>) getIntent().getSerializableExtra("usuarios");

        //tv_Nombre.setText(usuario.get(0).getNombre());

        cv_cambiarClave = findViewById(R.id.cv_cambiarClave);
        cv_cambiarNombre = findViewById(R.id.cv_cambiarNombre);
        cv_cerrarSesion = findViewById(R.id.cv_cerrarSesion);

        cv_cambiarClave.setOnClickListener(this);
        cv_cambiarNombre.setOnClickListener(this);
        cv_cerrarSesion.setOnClickListener(this);
    }

    private void cerrarSesion(){
        SharedPreferences preferences = getSharedPreferences("lista", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putBoolean("login",false);
        editor.commit();

        MainActivity.usuarios.clear();
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.cv_cambiarClave:
                 i = new Intent(getApplicationContext(),CambiarClaveActivity.class);
                startActivity(i);
                break;

            case R.id.cv_cambiarNombre:
                 i = new Intent(getApplicationContext(),CambiarNombreActivity.class);
                startActivity(i);
                break;

            case R.id.cv_cerrarSesion:
                cerrarSesion();
                PanelActivity.super.finish();
                break;

            default:
                break;
        }
    }
}
