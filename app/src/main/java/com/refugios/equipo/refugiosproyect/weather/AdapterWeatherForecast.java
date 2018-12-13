package com.refugios.equipo.refugiosproyect.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.refugios.equipo.refugiosproyect.R;
import com.refugios.equipo.refugiosproyect.weather.common.Common;
import com.refugios.equipo.refugiosproyect.weather.model.WeatherForecastResult;
import com.squareup.picasso.Picasso;

public class AdapterWeatherForecast extends RecyclerView.Adapter<AdapterWeatherForecast.ViewHolder> {

    //ATRIBUTOS
    Context context;
    WeatherForecastResult weatherForecastResult;

    public AdapterWeatherForecast(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;
    }

    //IMPLEMENTACION
    @NonNull
    @Override
    public AdapterWeatherForecast.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.esqueleto_forecast,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWeatherForecast.ViewHolder holder, int position) {

        //CARGAR ICONO
        Picasso.with(context).load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.list.get(position).weather.get(0).getIcon())
                .append(".png").toString()).into(holder.img_weather);

        holder.txt_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherForecastResult
        .list.get(position).dt)));

        holder.txt_description.setText(new StringBuilder(weatherForecastResult.list.get(position)
        .weather.get(0).getDescription()));

        holder.txt_temperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position)
                .main.getTemp())).append("°C"));

    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //ATRIBUTOS
        TextView txt_date_time, txt_description, txt_temperature;
        ImageView img_weather;

        //CONSTRUCTOR
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_weather = itemView.findViewById(R.id.imageView_weatherForecast);
            txt_date_time = itemView.findViewById(R.id.txt_date_forecast);
            txt_description = itemView.findViewById(R.id.txt_descripcion_forecast);
            txt_temperature = itemView.findViewById(R.id.txt_temperaturaForecast);

        }

    }
}
