package com.refugios.equipo.refugiosproyect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CambiarNombreActivity extends AppCompatActivity implements View.OnClickListener {

    //ATRIBUTOS
    private EditText etNombre;
    private String nombre;
    private CardView cvAceptar, cvCancelar;


    //IMPLEMENTACION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_nombre);

        etNombre = findViewById(R.id.editText_cambiarNombre);

        cvAceptar = findViewById(R.id.cv_aceptarNombre);
        cvCancelar = findViewById(R.id.cv_cancelarNombre);

        cvAceptar.setOnClickListener(this);
        cvCancelar.setOnClickListener(this);
    }

    private void nombre(){
        nombre = etNombre.getText().toString().trim();

        if (nombre.length() <= 0){
            etNombre.setError(getResources().getString(R.string.inserta_nombre));
        }else {
            String consulta="update usuario set nombre = '"+nombre+"' where id_usu = "+
                    MainActivity.usuarios.get(0).getId();
            new CambiarNombre(consulta).execute();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_aceptarNombre:
                nombre();
                break;
            case R.id.cv_cancelarNombre:
                etNombre.setText("");
                CambiarNombreActivity.super.finish();
                break;
        }
    }

    public class CambiarNombre extends AsyncTask<Object, Object, Integer> {

        String consulta;
        Connection connection;
        Statement statement;

        public CambiarNombre(String consulta) {
            this.consulta = consulta;

        }

        @Override
        protected Integer doInBackground(Object... objects) {
            int result = 0;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + BBDD.getIp() + BBDD.getBd(), BBDD.getUsuario(), BBDD.getClave());
                statement = connection.createStatement();
                publishProgress();

                result = statement.executeUpdate(consulta);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer resultSet) {
            super.onPostExecute(resultSet);

            if (resultSet == 1) {
                showToast( getResources().getString(R.string.nombre_cambiado));

                MainActivity.usuarios.get(0).setNombre(nombre);

                SharedPreferences preferences = getSharedPreferences("lista", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("nombre",nombre);

                editor.commit();

                CambiarNombreActivity.super.finish();

            } else {
                showToast( getResources().getString(R.string.error_cambiar_nombre));
                CambiarNombreActivity.super.finish();
            }

            try {
                connection.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//Fin AsynTack

    private void showToast( String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
    }
}
