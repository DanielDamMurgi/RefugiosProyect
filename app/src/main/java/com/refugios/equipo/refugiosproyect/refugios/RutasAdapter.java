package com.refugios.equipo.refugiosproyect.refugios;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.refugios.equipo.refugiosproyect.clasesPrincipales.Ruta;
import com.refugios.equipo.refugiosproyect.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class RutasAdapter extends RecyclerView.Adapter<RutasAdapter.ViewHolder> implements View.OnClickListener {

    //ATRIBUTOS
    private ArrayList<Ruta> rutas;
    private Context context;
    Intent intent ;
    private Refugio refugio;

    public RutasAdapter(Context context, ArrayList<Ruta> rutas, Refugio refugio) {
        this.context = context;
        this.rutas = new ArrayList<>();
        this.rutas = rutas;
        this.refugio = refugio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.esqueleto_rutas,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.with(context).load(rutas.get(position).getImagen().trim()).into(holder.imageView);
        holder.textView.setText(rutas.get(position).getNombre());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context,MapaRutasActivity.class);
                intent.putExtra("ruta",rutas.get(position).getKml());
                intent.putExtra("refugio",refugio);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rutas.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RefugioAdapter.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_rutas);
            imageView = itemView.findViewById(R.id.iv_imagenRuta);
            textView = itemView.findViewById(R.id.tv_nombreRuta);
        }
    }
}
