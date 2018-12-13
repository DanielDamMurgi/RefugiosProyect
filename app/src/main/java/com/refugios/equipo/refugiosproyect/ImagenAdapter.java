package com.refugios.equipo.refugiosproyect;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.refugios.equipo.refugiosproyect.clasesPrincipales.Imagen;

import java.util.ArrayList;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<Imagen> imagenes;

    public ImagenAdapter(Context context, ArrayList<Imagen> imagenes) {
        this.context=context;
        this.imagenes = imagenes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View imagenView = inflater.inflate(R.layout.esqueleto_imagenes, parent , false);
        ImagenAdapter.ViewHolder viewHolder = new ImagenAdapter.ViewHolder(imagenView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context)
                .load(imagenes.get(position).getUrl().trim())
                .placeholder(R.drawable.ic_foto)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imagen imagen = imagenes.get(position);
                Intent intent = new Intent(context, ImagenDetalleActivity.class);
                intent.putExtra(ImagenDetalleActivity.EXTRA_PHOTO, imagen);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //ATRIBUTOS
        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_imagenSierra);

        }

    }
}
