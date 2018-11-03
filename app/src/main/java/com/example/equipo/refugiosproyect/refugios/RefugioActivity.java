package com.example.equipo.refugiosproyect.refugios;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.equipo.refugiosproyect.clasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RefugioActivity extends AppCompatActivity {

    //ATRIBUTOS
    private RecyclerView mRecyclerView_refugios;
    private RecyclerView.LayoutManager mLayoutManager_refugios;
    private int idSierra;
    private ProgressDialog progressDialog;
    ActualizacionRefugio actualizacionRefugio;

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

    public class CargarRefugios extends AsyncTask<Void, Void, ResultSet> {
        android.app.AlertDialog dialog;
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        public CargarRefugios(String consulta, ProgressDialog dialog) {
            this.consulta = consulta;
            this.dialog = dialog;
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
                lanzarAdapter();

                connection.close();
                statement.cancel();
                this.resultSet.close();
//
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
