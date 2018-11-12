package com.example.equipo.refugiosproyect.refugios;

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

import com.bumptech.glide.Glide;
import com.example.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.R;

import java.util.ArrayList;

class RefugioAdapter extends RecyclerView.Adapter<RefugioAdapter.ViewHolder> implements View.OnClickListener {
    static Context context;
    private ArrayList<Refugio> refugios;
    private ViewHolder viewHolder;
    private Intent intent;
    private Refugio refugio;

    public RefugioAdapter() {

    }

    public RefugioAdapter(Context context, ArrayList<Refugio> refugios) {
        this.context = context;
        this.refugios = new ArrayList<>();
        this.refugios = refugios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.esqueleto_refugio, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String situacion;

        Glide.with(context)
                .load(refugios.get(position).getFoto().trim())
                .placeholder(R.drawable.ic_foto)
                .into(holder.imageViewRefugio);


        viewHolder.textViewNom.setText(refugios.get(position).getNombre());
        viewHolder.textViewAlt.setText(refugios.get(position).getAltitud() + " m");

        situacion = refugios.get(position).getSituacion();
        if (situacion.equalsIgnoreCase("abierto")){
            viewHolder.textViewSit.setTextColor(context.getResources().getColor(R.color.verde));

        }else{
            viewHolder.textViewSit.setTextColor(context.getResources().getColor(R.color.rojo));
        }

        viewHolder.textViewSit.setText(situacion);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context,RefugioPagerActivity.class);
                refugio = refugios.get(position);
                intent.putExtra("refugio",refugio);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return refugios.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textViewNom,textViewAlt, textViewSit;
        ImageView imageViewRefugio;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView_refugioList);
            imageViewRefugio = itemView.findViewById(R.id.imageView_imagenRefugioList);
            textViewNom = itemView.findViewById(R.id.textView_nombreRefugioList);
            textViewAlt = itemView.findViewById(R.id.textView_altitudRefugioList);
            textViewSit = itemView.findViewById(R.id.textView_situacionRefugioList);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }
}
