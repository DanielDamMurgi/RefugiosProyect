package com.example.equipo.refugiosproyect;

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

import com.example.equipo.refugiosproyect.ClasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.sierras.SierraActivity;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements View.OnClickListener {
    static Context context;
    private ArrayList<Sierra> sierras;
    private ViewHolder viewHolder;
    private Intent intent;

    public MainAdapter(){

    }

    public MainAdapter(Context context, ArrayList<Sierra> sierras) {
        this.context = context;
        this.sierras = new ArrayList<>();
        this.sierras = sierras;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sierras_esqueleto,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewHolder.textViewSierra.setText(sierras.get(position).getNombre());
        viewHolder.imageViewSierra.setImageResource(sierras.get(position).getFoto());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, SierraActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sierras.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textViewSierra;
        ImageView imageViewSierra;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_sierras);
            textViewSierra = itemView.findViewById(R.id.textView_sierras);
            imageViewSierra = itemView.findViewById(R.id.imageView_sierras);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }
}
