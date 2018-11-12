package com.example.equipo.refugiosproyect.refugios;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.clasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.clasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.sierras.MapsActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RefugioPanelActivity extends AppCompatActivity implements View.OnClickListener {

    //ATRIBUTOS
    private CardView cv_listaRefugios, cv_mapaRefugios;
    private ArrayList<Refugio> refugios = new ArrayList<>();
    private Sierra sierra;
    ActualizacionRefugio actualizacionRefugio;
    private ProgressDialog progressDialog;

    //IMPLEMENTACION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugio_panel);

        cv_listaRefugios = findViewById(R.id.cv_listaRefugios);
        cv_listaRefugios.setOnClickListener(this);

        cv_mapaRefugios = findViewById(R.id.cv_mapaRefugios);
        cv_mapaRefugios.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.cargando_refugios));

        sierra = (Sierra) getIntent().getExtras().getSerializable("sierra");

        if (refugios.isEmpty() || sierra.getId() != refugios.get(0).getId_sierra()){
            progressDialog.show();
            String consulta = "select * from refugio where id_sierra = "+sierra.getId();
            new CargarRefugios(consulta,progressDialog).execute();

            actualizacionRefugio=new ActualizacionRefugio();
            actualizacionRefugio.execute();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.cv_listaRefugios:
                i = new Intent(this, RefugioActivity.class);
                i.putExtra("refugios",refugios);
                startActivity(i);
                break;
            case R.id.cv_mapaRefugios:
                i = new Intent(this, MapsActivity.class);
                i.putExtra("latitudSierra", sierra.getLatutud());
                i.putExtra("longitudSierra", sierra.getLongitud());
                i.putExtra("nombreSierra", sierra.getNombre());
                i.putExtra("refugios", refugios);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    public class CargarRefugios extends AsyncTask<Void, Void, ResultSet> {
        android.app.AlertDialog dialog;
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        public CargarRefugios(String consulta, ProgressDialog progressDialog) {
            this.consulta = consulta;
            this.dialog = progressDialog;
        }

        @Override
        protected ResultSet doInBackground(Void... params) {
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
                    refugios.add(new Refugio(
                            resultSet.getInt("id_refugio"),
                            resultSet.getString("nombre"),
                            resultSet.getString("info"),
                            resultSet.getString("foto"),
                            resultSet.getString("situacion"),
                            resultSet.getString("altitud"),
                            resultSet.getString("latitud"),
                            resultSet.getString("longitud"),
                            resultSet.getInt("id_sierra")
                    ));
                }

                connection.close();
                statement.cancel();
                this.resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

    }//FIN CARGARSIERRA

    public class ActualizacionRefugio extends AsyncTask<Void, Void, Void> {

        public ActualizacionRefugio() {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... voids) {
            super.onProgressUpdate();
            progressDialog.dismiss();
        }
    }//Fin AsynTack

}
