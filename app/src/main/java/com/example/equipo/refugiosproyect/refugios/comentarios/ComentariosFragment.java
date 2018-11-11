package com.example.equipo.refugiosproyect.refugios.comentarios;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.clasesPrincipales.Mensaje;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentariosFragment extends Fragment {

    //ATRIBUTOS
    private RecyclerView recyclerView_mensaje;
    private EditText txt_mensaje;
    private Button btn_enviar;
    private AdapterMensaje adapterMensaje;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //IMPLEMENTACION

    public ComentariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comentarios, container, false);

        recyclerView_mensaje = view.findViewById(R.id.recycler_chat);
        txt_mensaje = view.findViewById(R.id.txt_chat);
        btn_enviar = view.findViewById(R.id.bt_chat);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat"); //se guardan los mensajes

        adapterMensaje = new AdapterMensaje(getActivity());
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        recyclerView_mensaje.setLayoutManager(l);
        recyclerView_mensaje.setAdapter(adapterMensaje);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Mensaje(txt_mensaje.getText().toString(),"dani","1","00:00"));
            }
        });

        adapterMensaje.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje m = dataSnapshot.getValue(Mensaje.class);
                adapterMensaje.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void setScrollBar(){
        recyclerView_mensaje.scrollToPosition(adapterMensaje.getItemCount()-1);
    }

}
