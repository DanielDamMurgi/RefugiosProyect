package com.example.equipo.refugiosproyect.sierras;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.equipo.refugiosproyect.ClasesPrincipales.BBDD;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.refugios.RefugioActivity;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;

public class SierraFragment extends Fragment implements View.OnClickListener{

    private CardView infoCard, refugioCard, fotosCard, mapaCard;
    private Sierra sierra;
    private ArrayList<Refugio> refugios = new ArrayList<>();
    ActualizacionRefugio actualizacionRefugio;

    public SierraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sierra, container, false);

        infoCard = view.findViewById(R.id.cardView_infoSierra);
        infoCard.setOnClickListener(this);

        refugioCard = view.findViewById(R.id.cardView_refugioSierra);
        refugioCard.setOnClickListener(this);

        fotosCard = view.findViewById(R.id.cardView_fotosSierras);
        fotosCard.setOnClickListener(this);

        mapaCard = view.findViewById(R.id.cardView_mapaSierra);
        mapaCard.setOnClickListener(this);

        sierra = (Sierra) getActivity().getIntent().getExtras().getSerializable("sierra");


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (refugios.isEmpty() || sierra.getId() != refugios.get(0).getId_sierra()){

            String consulta = "select * from refugio where id_sierra = "+sierra.getId();
            new CargarRefugios(consulta).execute();

            actualizacionRefugio=new ActualizacionRefugio();
            actualizacionRefugio.execute();


        }
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.cardView_infoSierra:
                i = new Intent(getActivity(),InfoActivity.class);
                i.putExtra("infoSierra",sierra.getInfo());
                i.putExtra("nombreSierra",sierra.getNombre());
                i.putExtra("fotoSierra",sierra.getFoto());
                startActivity(i);
                break;

            case R.id.cardView_refugioSierra:
                if (refugios.isEmpty()){
                    Toast.makeText(getContext(),"Cargando refugios",Toast.LENGTH_LONG).show();
                }else {
                    i = new Intent(getActivity(),RefugioActivity.class);
                    i.putExtra("refugios",refugios);
                    startActivity(i);
                }

                break;

            case R.id.cardView_fotosSierras:
                i = new Intent(getActivity(), FotosSierraActivity.class);
                i.putExtra("idSierra",sierra.getId());
                startActivity(i);
                break;

            case R.id.cardView_mapaSierra:
                if (refugios.isEmpty()){
                    Toast.makeText(getContext(),"Cargando refugios",Toast.LENGTH_LONG).show();
                }else {
                    i = new Intent(getActivity(), MapsActivity.class);
                    i.putExtra("latitudSierra", sierra.getLatutud());
                    i.putExtra("longitudSierra", sierra.getLongitud());
                    i.putExtra("nombreSierra", sierra.getNombre());
                    i.putExtra("refugios", refugios);
                    startActivity(i);
                }
                break;
            default:

                break;
        }

    }

    public class CargarRefugios extends AsyncTask<Void, Void, ResultSet> {
        String consulta;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        public CargarRefugios(String consulta) {
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
        }
    }//Fin AsynTack
}
