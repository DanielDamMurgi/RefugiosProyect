package com.example.equipo.refugiosproyect.sierras;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;

import com.example.equipo.refugiosproyect.ClasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Imagen;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.ImagenAdapter;
import com.example.equipo.refugiosproyect.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FotosSierraActivity extends AppCompatActivity {

    private ArrayList<Imagen> imagenesSierra;
    private int id_sierra;
    private RecyclerView recyclerView;
    private ActualizacionFotoSierra actualizacionFotoSierra;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_sierra);

        imagenesSierra = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView = findViewById(R.id.rv_imagenesSierra);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //cargarDatos();

    }

    @Override
    protected void onStart() {
        super.onStart();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando fotos");

        id_sierra = getIntent().getExtras().getInt("idSierra");

        if (imagenesSierra.isEmpty() || imagenesSierra.get(0).getId() != id_sierra){
            imagenesSierra.clear();
            progressDialog.show();
            String consulta = "select distinct * from foto_sierra where id_sierra = "+id_sierra;
            new CargarFotosSierras(consulta,progressDialog).execute();
            actualizacionFotoSierra = new ActualizacionFotoSierra();
            actualizacionFotoSierra.execute();
        }else{
            lanzarAdapter();
        }
    }

    private void lanzarAdapter(){
        ImagenAdapter adapter = new ImagenAdapter(this, imagenesSierra);
        recyclerView.setAdapter(adapter);
    }

    public class CargarFotosSierras extends AsyncTask<Void, Void, ResultSet> {
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        android.app.AlertDialog dialog;

        public CargarFotosSierras(String consulta, ProgressDialog dialog) {
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
                    imagenesSierra.add(new Imagen(
                            resultSet.getInt("id_foto"),
                            resultSet.getInt("id_sierra"),
                            resultSet.getString("foto")
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

    public class ActualizacionFotoSierra extends AsyncTask<Void, Void, Void> {

        public ActualizacionFotoSierra() {
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
