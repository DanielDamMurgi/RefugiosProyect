package com.example.equipo.refugiosproyect;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equipo.refugiosproyect.clasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.clasesPrincipales.Sierra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainFragment extends Fragment {
    //ATRIBUTOS
    private RecyclerView mRecyclerView_sierras;
    private RecyclerView.LayoutManager mLayoutManager_sierras;
    private Sierra sierra;
    private ArrayList<Sierra> sierras = new ArrayList<>();
    private ProgressDialog progressDialog;
    ActualizacionSierra actualizacionSierra;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando sierras");

        if (sierras.isEmpty()){
            progressDialog.show();
            String consulta = "select * from sierra";
            new CargarSierras(consulta,progressDialog).execute();
            actualizacionSierra = new ActualizacionSierra();
            actualizacionSierra.execute();
        }else{
            lanzarAdapter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView_sierras = view.findViewById(R.id.recyclerView_sierras);

        mLayoutManager_sierras = new LinearLayoutManager(view.getContext());
        mRecyclerView_sierras.setLayoutManager(mLayoutManager_sierras);

        return view;
    }

    public void lanzarAdapter() {
        MainAdapter adapter = new MainAdapter(getActivity(), sierras);
        mRecyclerView_sierras.setLayoutManager(mLayoutManager_sierras);
        mRecyclerView_sierras.setAdapter(adapter);
    }

    public class CargarSierras extends AsyncTask<Void, Void, ResultSet> {
        android.app.AlertDialog dialog;
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        public CargarSierras(String consulta, ProgressDialog dialog) {
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
                    sierras.add(new Sierra(
                            resultSet.getInt("id_sierra"),
                            resultSet.getString("nombre"),
                            resultSet.getString("info"),
                            resultSet.getString("imagen"),
                            resultSet.getString("latitud"),
                            resultSet.getString("longitud")
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

    public class ActualizacionSierra extends AsyncTask<Void, Void, Void> {

        public ActualizacionSierra() {
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
