package com.example.equipo.refugiosproyect.weather;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.weather.common.Common;
import com.example.equipo.refugiosproyect.weather.model.WeatherResult;
import com.example.equipo.refugiosproyect.weather.retrofit.IOpenWeatherMap;
import com.example.equipo.refugiosproyect.weather.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class HoyWeatherFragment extends Fragment {

    //ATRIBUTOS
    static HoyWeatherFragment instancia;
    private ImageView imageView;
    private TextView txt_nombreSierra, txt_humidity, txt_sunrise, txt_sunset, txt_pressure, txt_temperature, txt_descripcion, txt_date_time, txt_wind, txt_geo_coord;
    private LinearLayout weather_panel;
    private ProgressBar loading_weather;
    private CompositeDisposable compositeDisposable;
    private IOpenWeatherMap mService;
    private Boolean locat;

    //GETTERS Y SETTERS
    public static HoyWeatherFragment getInstancia() {
        if (instancia == null) {
            instancia = new HoyWeatherFragment();
        }
        return instancia;
    }

    //IMPLEMENTACION

    public HoyWeatherFragment() {
        // Required empty public constructor
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstancia();
        mService = retrofit.create(IOpenWeatherMap.class);
    }

    @Override
    public void onStart() {
        super.onStart();
       // compositeDisposable.clear();
    }



    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoy_weather, container, false);

        imageView = view.findViewById(R.id.imageView_weather);
        txt_nombreSierra = view.findViewById(R.id.txt_nombre_sierra);
        txt_humidity = view.findViewById(R.id.txt_humidity);
        txt_sunrise = view.findViewById(R.id.txt_sunrise);
        txt_sunset = view.findViewById(R.id.txt_sunset);
        txt_pressure = view.findViewById(R.id.txt_pressure);
        txt_temperature = view.findViewById(R.id.txt_temperatura);
        txt_descripcion = view.findViewById(R.id.txt_descripcion);
        txt_date_time = view.findViewById(R.id.txt_date_time);
        txt_wind = view.findViewById(R.id.txt_wind);
        txt_geo_coord = view.findViewById(R.id.txt_geo_coord);

        weather_panel = view.findViewById(R.id.panel_weather);
        loading_weather = view.findViewById(R.id.loading_weather);

        locat = getActivity().getIntent().getExtras().getBoolean("locat");

        getWeatherInformation();

        return view;
    }

    private void getWeatherInformation() {

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

        compositeDisposable.add(mService.getWeatherByLatLng(lat,
                lon,
                Common.WEATHER_IP,"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {

                        //CARGAR IMAGEN
                        Picasso.with(getContext()).load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherResult.getWeather().get(0).getIcon())
                                .append(".png").toString()).into(imageView);

                        if (nombre.length() <= 0){
                            txt_nombreSierra.setText(weatherResult.getName());
                            txt_descripcion.setText(new StringBuilder("Weather in ")
                                    .append(weatherResult.getName()).toString());
                            txt_geo_coord.setText(new StringBuilder("").append(weatherResult.getCoord().toString()).append("").toString());
                        }else{
                            txt_nombreSierra.setText(nombre);
                            txt_descripcion.setText(new StringBuilder("Weather in ")
                                    .append(nombre));
                            txt_geo_coord.setText(new StringBuilder("[").append(lat).append(',').append(lon).append(']').toString());
                        }

                        txt_temperature.setText(new StringBuilder(
                                String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                        txt_date_time.setText(Common.convertUnixToDate(weatherResult.getDt()));
                        txt_pressure.setText(new StringBuilder(
                                String.valueOf(weatherResult.getMain().getPressure())).append(" hpa").toString());
                        txt_humidity.setText(new StringBuilder(
                                String.valueOf(weatherResult.getMain().getHumidity())).append(" %").toString());
                        txt_sunrise.setText(Common.convertUnixToHour(weatherResult.getSys().getSunrise()));
                        txt_sunset.setText(Common.convertUnixToHour(weatherResult.getSys().getSunset()));
                        txt_geo_coord.setText(new StringBuilder("").append(weatherResult.getCoord().toString()).append("").toString());
                        txt_wind.setText(
                                new StringBuilder("Speed: ").append(String.valueOf(weatherResult.getWind().getSpeed())).append(" Deg: ").append(weatherResult.getWind().getDeg()).toString());

                        //Display panel
                        weather_panel.setVisibility(View.VISIBLE);
                        loading_weather.setVisibility(View.GONE);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(),""+throwable.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }));
    }

}
