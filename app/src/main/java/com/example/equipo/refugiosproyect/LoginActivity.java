package com.example.equipo.refugiosproyect;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.equipo.refugiosproyect.ClasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Usuario;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cv;
    private static boolean login=false;
    public static Usuario usuario;
    private EditText etCorreo, etClave;
    private String correo,clave;


    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        LoginActivity.login = login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cv = findViewById(R.id.cardView_login_LA);
        cv.setOnClickListener(this);

        etCorreo = findViewById(R.id.editText_email);
        etClave = findViewById(R.id.editText_clave);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardView_login_LA:
                if (!validarEmail(etCorreo.getText().toString().trim())){
                    etCorreo.setError("Email no válido");
                    etCorreo.requestFocus();
                }else if (!ComprobarClave()){
                    etClave.setError("Inserta la contraseña");
                    etClave.requestFocus();
            }else{
                    String consulta = "select * from usuario where correo='"+correo+"' and clave='"+clave+"'";
                    new LoginUsuario(consulta).execute();
                    finish();
                }
                break;
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

    private boolean ComprobarClave() {
        clave = etClave.getText().toString().trim();
        if (clave.length() == 0){
            return false;
        }else{
            return true;
        }
    }


    public class LoginUsuario extends AsyncTask<Void,Void,ResultSet>{
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        public LoginUsuario(String consulta){
            this.consulta = consulta;
        }

        @Override
        protected ResultSet doInBackground(Void... voids) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + BBDD.getIp() + BBDD.getBd(), BBDD.getUsuario(), BBDD.getClave());
                statement = connection.createStatement();
                publishProgress();

                resultSet = statement.executeQuery(consulta);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return resultSet;
        }

        @Override
        protected void onPostExecute(ResultSet resultSet) {
            super.onPostExecute(resultSet);


                try {
                    while (resultSet.next()) {
                        usuario = new Usuario(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4));
                    }
                    connection.close();
                    statement.cancel();
                    this.resultSet.close();

                    if (usuario == null){
                        Toast.makeText(getApplicationContext(),"Credenciales incorrectas",Toast.LENGTH_LONG).show();
                    }else{
                        login = true;
                        MainActivity.usuario.add(usuario);
                        MainActivity.ActualizarEstado(login,getApplicationContext());
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }
}
