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

public class CambiarClaveActivity extends AppCompatActivity implements View.OnClickListener {

    //ATRIBUTOS
    private CardView cardViewAceptar, cardViewCancelar;
    private EditText etClaveAntigua, etClaveNueva, etClaveNuevaR;
    private String claveAntigua, clave1, clave2;

    //IMPLEMENTACION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);

        etClaveAntigua = findViewById(R.id.editText_cambiarNombre);
        etClaveNueva = findViewById(R.id.editText_claveNueva);
        etClaveNuevaR = findViewById(R.id.editText_repClaveNueva);

        cardViewAceptar = findViewById(R.id.cv_aceptarClave);
        cardViewCancelar = findViewById(R.id.cv_cancelarClave);

        cardViewAceptar.setOnClickListener(this);
        cardViewCancelar.setOnClickListener(this);
    }

    private void cambiarClave() {
        claveAntigua = etClaveAntigua.getText().toString().trim();
        clave1 = etClaveNueva.getText().toString().trim();
        clave2 = etClaveNuevaR.getText().toString().trim();

        if (comprobarClaveAnt()){
            if (validarContraseña()){
                String consulta="update usuario set clave = '"+clave1+"' where id_usu = "+MainActivity.usuarios.get(0).getId();
                new CambiarClave(consulta).execute();
                CambiarClaveActivity.super.finish();
            }
        }
    }

    //COMPRUEBA QUE LAS CLAVE ANTIGUA SEA CORRENTA
    private boolean comprobarClaveAnt(){
        if (claveAntigua.length() <= 0){
            etClaveAntigua.setError(getResources().getString(R.string.insertar_clave));
            return false;
        }else {
            if (claveAntigua.equals(MainActivity.usuarios.get(0).getClave())){

                return true;
            }else {
                etClaveAntigua.setText("");
                etClaveAntigua.setError(getResources().getString(R.string.clave_incorrecta));
                return false;
            }
        }
    }

    //COMPRUEBA QUE LA CLAVE NUEVA TENGA UN MIN DE CARACTERES Y COINCIDAN LAS DOS
    private boolean validarContraseña() {
        if (clave1.length() > 0) {
            if (clave1.length() < 9) {
                etClaveNueva.setError(getResources().getString(R.string.clave_min_caracteres));
                return false;
            } else {
                if (clave1.equals(clave2)) {
                    return true;
                } else {
                    etClaveNueva.setError(getResources().getString(R.string.clave_no_coinciden));
                    return false;
                }
            }
        } else {
            etClaveNueva.setError(getResources().getString(R.string.insertar_clave));
            return false;
        }

    }//FIN VALIDAR CONTRASEÑA

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_aceptarClave:
                cambiarClave();
                break;
            case R.id.cv_cancelarClave:
                etClaveAntigua.setText("");
                etClaveNueva.setText("");
                etClaveNuevaR.setText("");
                CambiarClaveActivity.super.finish();
                break;
            default:
                break;
        }
    }

    public class CambiarClave extends AsyncTask<Object, Object, Integer> {

        String consulta;
        Connection connection;
        Statement statement;

        public CambiarClave(String consulta) {
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
                showToast( getResources().getString(R.string.clave_cambiada));

                MainActivity.usuarios.get(0).setClave(clave1);

                SharedPreferences preferences = getSharedPreferences("lista", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("clave",clave1);
                editor.commit();

            } else {
                showToast( getResources().getString(R.string.error_cambiar_clave));
            }

            try {
                connection.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            //dialog.dismiss();
        }
    }//Fin AsynTack

    private void showToast( String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
    }
}
