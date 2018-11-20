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

import com.example.equipo.refugiosproyect.clasesPrincipales.Sierra;
import com.example.equipo.refugiosproyect.sierras.SierraActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements View.OnClickListener {
    static Context context;
    private ArrayList<Sierra> sierras;
    private ViewHolder viewHolder;
    private Intent intent;
    private Sierra sierra;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.esqueleto_sierras,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        viewHolder.textViewSierra.setText(sierras.get(position).getNombre());

        Picasso.with(context).load(sierras.get(position).getFoto().trim()).into(viewHolder.imageViewSierra);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, SierraActivity.class);
                sierra = new Sierra(sierras.get(position).getId(),sierras.get(position).getNombre(),
                        sierras.get(position).getInfo(),sierras.get(position).getFoto(),sierras.get(position).getLatutud(),
                        sierras.get(position).getLongitud());
                intent.putExtra("sierra", sierra);
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
