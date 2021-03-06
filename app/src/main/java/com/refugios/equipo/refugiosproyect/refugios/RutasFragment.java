package com.refugios.equipo.refugiosproyect.refugios;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refugios.equipo.refugiosproyect.LiveData;
import com.refugios.equipo.refugiosproyect.clasesPrincipales.BBDD;
import com.refugios.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.refugios.equipo.refugiosproyect.clasesPrincipales.Ruta;
import com.refugios.equipo.refugiosproyect.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RutasFragment extends Fragment {

    private ArrayList<Ruta> rutas = new ArrayList<>();
    private RecyclerView rv_rutas;
    private RecyclerView.LayoutManager lm_rutas;
    private static Refugio refugio;

    public RutasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutas, container, false);

        rv_rutas = view.findViewById(R.id.rv_rutas);
        lm_rutas = new LinearLayoutManager(getActivity());

        lanzarAdapter();

        return view;
    }

    public static void getIdRefugio(Refugio refugio){
        RutasFragment.refugio = refugio;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!LiveData.getRutas().isEmpty()){
            rutas.clear();
            rutas = LiveData.getRutas();
        }

        if (rutas.isEmpty()){
            rutas.clear();
            String consulta = "select * from ruta where id_refugio = "+refugio.getId();
            new CargarRutas(consulta).execute();
        }else if(rutas.get(0).getId_refugio()!=refugio.getId()) {
            rutas.clear();
            String consulta = "select * from ruta where id_refugio = "+refugio.getId();
            new CargarRutas(consulta).execute();
        }else{
            lanzarAdapter();
        }
    }

    @Override
    public void onStop() {
        LiveData.setRutas(rutas);
        super.onStop();
    }

    public void lanzarAdapter() {
        RutasAdapter adapter = new RutasAdapter(getActivity(), rutas,refugio);
        rv_rutas.setLayoutManager(lm_rutas);
        rv_rutas.setAdapter(adapter);
    }

    public class CargarRutas extends AsyncTask<Void, Void, ResultSet> {
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        public CargarRutas(String consulta) {
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
                    rutas.add(new Ruta(
                            resultSet.getInt("id_ruta"),
                            resultSet.getInt("id_refugio"),
                            resultSet.getString("kml"),
                            resultSet.getString("foto"),
                            resultSet.getString("nombre")
                    ));
                }
                lanzarAdapter();

                connection.close();
                statement.cancel();
                this.resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }//FIN CARGARSIERRA

}
