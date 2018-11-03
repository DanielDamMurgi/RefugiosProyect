package com.example.equipo.refugiosproyect;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.equipo.refugiosproyect.clasesPrincipales.BBDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private Operaciones operaciones;
    private CardView cardView;
    private EditText etCorreo, etNombre, etClave1, etClave2;
    private String correo, nombre, clave1, clave2;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //dialog  = new SpotsDialog(this,"Insertando cliente...");

        cardView = findViewById(R.id.cardView_registrar_RA);
        etCorreo = findViewById(R.id.editText_emailReg);
        etNombre = findViewById(R.id.editText_nombreReg);
        etClave1 = findViewById(R.id.editText_claveReg);
        etClave2 = findViewById(R.id.editText_repClaveReg);

        cardView.setOnClickListener(this);


    }

    private boolean validarNombre(String nombre) {
        if (nombre.length()<=0) {
            //Toast.makeText(this, "Inserta el nombre",Toast.LENGTH_LONG).show();
            return false;
        } else {
            this.nombre = nombre;
            return true;
        }
    }

    //Retturn true si el correo es valido
    private boolean validarEmail(String email) {
        correo = email;
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
//TODO: TRADUCCIONES
    //Return true si la contraseña es valida
    private boolean validarContraseña() {
        clave1 = etClave1.getText().toString().trim();
        clave2 = etClave2.getText().toString().trim();

        if (clave1.length() > 0) {
            if (clave1.length() < 9) {
                etClave1.setError(getResources().getString(R.string.clave_min_caracteres));
                return false;
            } else {
                if (clave1.equals(clave2)) {
                    return true;
                } else {
                    etClave1.setError(getResources().getString(R.string.clave_no_coinciden));
                    return false;
                }
            }
        } else {
            etClave1.setError(getResources().getString(R.string.insertar_clave));
            return false;
        }

    }//FIN VALIDAR CONTRASEÑA

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardView_registrar_RA:
                if (!validarEmail(etCorreo.getText().toString().trim())) {
                    etCorreo.setError(getResources().getString(R.string.correo_no_valido));
                    etCorreo.requestFocus();
                    break;
                } else if (!validarNombre(etNombre.getText().toString().trim())) {
                    etNombre.setError(getResources().getString(R.string.inserta_nombre));
                    etNombre.requestFocus();
                    break;
                } else if (!validarContraseña()) {
                    etClave1.requestFocus();
                    break;
                } else {
                    String insert = "INSERT INTO usuarios (correo, nombre, clave) "
                            + "VALUES ('" + correo + "', '" + nombre + "', "
                            + "'" + clave1 + "');";

                    new registrarUsuario(insert).execute();

                    RegistroActivity.super.finish();
                }
                break;
        }
    }

    public class registrarUsuario extends AsyncTask<Object, Object, Integer> {

        String consulta;
        Connection connection;
        Statement statement;

        public registrarUsuario(String consulta) {
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
                showToast("Usuario No se pudo insertar.");
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer resultSet) {
            super.onPostExecute(resultSet);

            if (resultSet == 1) {
                showToast( "Usuario Registrado con éxito.");

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            } else {
                showToast( "Usuario No se pudo registrar.");
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
