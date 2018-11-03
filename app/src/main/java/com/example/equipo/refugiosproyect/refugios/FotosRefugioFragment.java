package com.example.equipo.refugiosproyect.refugios;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.clasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.clasesPrincipales.Imagen;
import com.example.equipo.refugiosproyect.ImagenAdapter;
import com.example.equipo.refugiosproyect.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FotosRefugioFragment extends Fragment {

    private ProgressDialog progressDialog;
    private ArrayList<Imagen> fotosRefugios;
    private RecyclerView recyclerView;
    private static int id_refugio;
    ActualizacionFotoRefugio actualizacionFotoRefugio;

    public FotosRefugioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fotos_refugio, container, false);

        fotosRefugios = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView = view.findViewById(R.id.rv_fotosRefugios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);



        return  view;
    }

    public static void setId(int id){
        id_refugio = id;
    }

    @Override
    public void onStart() {
        super.onStart();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando fotos");

        if (fotosRefugios.isEmpty() || fotosRefugios.get(0).getId() != id_refugio){
            fotosRefugios.clear();
            //progressDialog.show();
            String consulta = "select distinct * from foto_refugio where id_refugio = "+id_refugio;
            new CargarFotosRefugios(consulta,progressDialog).execute();
            actualizacionFotoRefugio = new ActualizacionFotoRefugio();
            actualizacionFotoRefugio.execute();
        }else{
            lanzarAdapter();
        }
    }

    private void lanzarAdapter(){
        ImagenAdapter adapter = new ImagenAdapter(getContext(), fotosRefugios);
        recyclerView.setAdapter(adapter);
    }

    public class CargarFotosRefugios extends AsyncTask<Void, Void, ResultSet> {
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        android.app.AlertDialog dialog;

        public CargarFotosRefugios(String consulta, ProgressDialog dialog) {
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
                    fotosRefugios.add(new Imagen(
                            resultSet.getInt("id_foto"),
                            resultSet.getInt("id_refugio"),
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

    public class ActualizacionFotoRefugio extends AsyncTask<Void, Void, Void> {

        public ActualizacionFotoRefugio() {
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
