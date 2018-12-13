package com.refugios.equipo.refugiosproyect.sierras;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.BBDD;
import com.refugios.equipo.refugiosproyect.clasesPrincipales.Imagen;
import com.refugios.equipo.refugiosproyect.ImagenAdapter;
import com.refugios.equipo.refugiosproyect.R;

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
    private ProgressBar loadind_fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_sierra);

        imagenesSierra = new ArrayList<>();

        loadind_fotos = findViewById(R.id.loading_fotosSierra);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView = findViewById(R.id.rv_imagenesSierra);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        id_sierra = getIntent().getExtras().getInt("idSierra");

        if (imagenesSierra.isEmpty() || imagenesSierra.get(0).getId() != id_sierra){
            loadind_fotos.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            imagenesSierra.clear();
            String consulta = "select distinct * from foto_sierra where id_sierra = "+id_sierra;
            new CargarFotosSierras(consulta).execute();

        }else{
            loadind_fotos.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
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

        public CargarFotosSierras(String consulta) {
            this.consulta = consulta;
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

            loadind_fotos.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        }

    }//FIN CARGARSIERRA

}
