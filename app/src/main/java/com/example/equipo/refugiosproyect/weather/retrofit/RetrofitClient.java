package com.example.equipo.refugiosproyect.weather.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instancia;

    public static Retrofit getInstancia(){
        if (instancia == null){
            instancia = new Retrofit.Builder()
                    .baseUrl("https://samples.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instancia;
    }
}
