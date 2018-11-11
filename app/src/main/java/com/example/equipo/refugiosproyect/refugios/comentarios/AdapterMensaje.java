package com.example.equipo.refugiosproyect.refugios.comentarios;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.clasesPrincipales.Mensaje;

import java.util.ArrayList;

public class AdapterMensaje extends RecyclerView.Adapter<AdapterMensaje.ViewHolder> {

    private ArrayList<Mensaje> mensajes = new ArrayList<>();
    private Context context;

    public AdapterMensaje(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMensaje.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.esqueleto_comentarios, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMensaje.ViewHolder holder, int position) {

        holder.nombre.setText(mensajes.get(position).getNombre());
        holder.mensaje.setText(mensajes.get(position).getMensaje());
        holder.hora.setText(mensajes.get(position).getHora());

    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public void addMensaje(Mensaje m){
        mensajes.add(m);
        notifyItemInserted(mensajes.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView hora;
        TextView mensaje;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txt_nombre_mensaje);
            hora = itemView.findViewById(R.id.txt_hora_mensaje);
            mensaje = itemView.findViewById(R.id.txt_mensaje_mensaje);
        }

    }
}
