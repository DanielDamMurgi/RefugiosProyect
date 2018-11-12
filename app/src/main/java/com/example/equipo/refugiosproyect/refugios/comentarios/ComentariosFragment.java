package com.example.equipo.refugiosproyect.refugios.comentarios;


import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.equipo.refugiosproyect.LoginActivity;
import com.example.equipo.refugiosproyect.MainActivity;
import com.example.equipo.refugiosproyect.MainFragment;
import com.example.equipo.refugiosproyect.R;
import com.example.equipo.refugiosproyect.clasesPrincipales.Mensaje;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.Executor;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentariosFragment extends Fragment {

    //ATRIBUTOS
    private RecyclerView recyclerView_mensaje;
    private EditText txt_mensaje;
    private Button btn_enviar;
    private ImageButton btn_foto;
    private AdapterMensaje adapterMensaje;
    private static int id_refugio;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int FOTO_ENVIAR = 1;

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
        //btn_foto = view.findViewById(R.id.btn_fotoChat);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(String.valueOf(id_refugio)); //se guardan los mensajes
        storage = FirebaseStorage.getInstance();

        adapterMensaje = new AdapterMensaje(getActivity());
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        recyclerView_mensaje.setLayoutManager(l);
        recyclerView_mensaje.setAdapter(adapterMensaje);

        if (LoginActivity.isLogin()){
            btn_enviar.setEnabled(true);
            txt_mensaje.setEnabled(true);
            txt_mensaje.setHint(getResources().getString(R.string.comentario));

        }else{
            btn_enviar.setEnabled(false);
            txt_mensaje.setEnabled(false);
            txt_mensaje.setHint(getResources().getString(R.string.inicie_sesion));
        }

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Mensaje(txt_mensaje.getText().toString(),MainActivity.usuarios.get(0).getEmail(),MainActivity.usuarios.get(0).getNombre(),"1","00:00"));
            }
        });

//        btn_foto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.setType("image/jpeg");
//                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
//                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),FOTO_ENVIAR);
//            }
//        });

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

    public static void setId(int id){
        ComentariosFragment.id_refugio = id;
    }

    private void setScrollBar(){
        recyclerView_mensaje.scrollToPosition(adapterMensaje.getItemCount()-1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FOTO_ENVIAR && requestCode == RESULT_OK){
            final Uri uri = data.getData();
            storageReference = storage.getReference("imagenes");//carpeta_imagenes
            final StorageReference fotoReferencia = storageReference.child(uri.getLastPathSegment());
            fotoReferencia.putFile(uri).addOnSuccessListener((Executor) this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    //Uri uri = taskSnapshot
                    storageReference.getDownloadUrl();
                    Mensaje m = new Mensaje("",uri.toString(),"dani","2","00:00");
                    databaseReference.push().setValue(m);
                }
            });
        }
    }
}
