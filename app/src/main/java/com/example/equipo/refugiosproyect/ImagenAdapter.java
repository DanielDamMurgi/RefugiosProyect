package com.example.equipo.refugiosproyect;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Imagen;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private Imagen[] imagenes;

    public ImagenAdapter(Context context, Imagen[] imagenes) {
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
        Imagen imagen = imagenes[position];
        ImageView imageView = holder.imageView;

        Glide.with(context)
                .load(imagen.getUrl())
                .placeholder(R.drawable.ic_account_circle_white_24dp)
                .into(imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imagen imagen = imagenes[position];
                Intent intent = new Intent(context, ImagenDetalleActivity.class);
                intent.putExtra(ImagenDetalleActivity.EXTRA_PHOTO, imagen);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagenes.length;
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
