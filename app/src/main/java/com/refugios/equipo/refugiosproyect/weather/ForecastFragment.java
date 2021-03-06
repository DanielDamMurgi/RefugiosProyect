package com.refugios.equipo.refugiosproyect.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.refugios.equipo.refugiosproyect.R;
import com.refugios.equipo.refugiosproyect.weather.common.Common;
import com.refugios.equipo.refugiosproyect.weather.model.WeatherForecastResult;
import com.refugios.equipo.refugiosproyect.weather.retrofit.IOpenWeatherMap;
import com.refugios.equipo.refugiosproyect.weather.retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    //ATRIBUTOS
    TextView txt_nombreSierra, txt_geo_coord;
    RecyclerView recyclerView;
    private boolean locat;

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;
    static ForecastFragment instancia;

    public static ForecastFragment getInstancia(){
        if (instancia == null){
            instancia = new ForecastFragment();
        }
        return instancia;
    }

    //CONSTRUCTOR
    public ForecastFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstancia();
        mService = retrofit.create(IOpenWeatherMap.class);
    }

    //IMPLEMENTACION


    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        txt_nombreSierra = view.findViewById(R.id.txt_sierraNombre_forecast);
        txt_geo_coord = view.findViewById(R.id.txt_geo_coord_forecast);

        recyclerView = view.findViewById(R.id.recycler_forecast);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        locat = getActivity().getIntent().getExtras().getBoolean("locat");

        getForecastWeatherInformation();

        return view;
    }

    private void getForecastWeatherInformation() {

        final String nombre, lat, lon;

        if (locat){
            nombre = getActivity().getIntent().getExtras().getString("nombreSierra");
            lat = getActivity().getIntent().getExtras().getString("latitudSierra");
            lon = getActivity().getIntent().getExtras().getString("longitudSierra");
        }else {
            lat = String.valueOf(Common.current_location.getLatitude());
            lon =  String.valueOf(Common.current_location.getLongitude());
            nombre = "";
        }

        compositeDisposable.add(mService.getForecastWeatherByLatLng(
                lat,
                lon,
                Common.WEATHER_IP, "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResult>() {
                               @Override
                               public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
                                   displayForecastWeather(weatherForecastResult, nombre, lat, lon);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                  Log.d("ERRORES",""+throwable.getMessage());
                               }
                           })
                );
    }

    private void displayForecastWeather(WeatherForecastResult weatherForecastResult, String nombre, String lat, String lon) {
        if (nombre.length() <= 0){
            txt_nombreSierra.setText(new StringBuilder(weatherForecastResult.city.name));
            txt_geo_coord.setText(new StringBuilder(weatherForecastResult.city.coord.toString()));
        }else{
            txt_nombreSierra.setText(new StringBuilder(nombre));
            txt_geo_coord.setText(new StringBuilder("[").append(lat).append(',').append(lon).append(']').toString());
        }

        AdapterWeatherForecast adapterWeatherForecast = new AdapterWeatherForecast(getContext(),weatherForecastResult);
        recyclerView.setAdapter(adapterWeatherForecast);
    }

}
