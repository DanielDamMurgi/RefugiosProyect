package com.example.equipo.refugiosproyect.sierras;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.equipo.refugiosproyect.R;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    private TextView textView;
    private Toolbar toolbar;
    private ImageView imagen;
    private static final String EXTRA_IMAGE = "com.example.equipo.refugiosproyect.extraimage";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String info,nombre, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        toolbar = findViewById(R.id.toolbar_refugios);
        textView = findViewById(R.id.textView_informacionSierras);

        info = getIntent().getExtras().getString("infoSierra");
        nombre = getIntent().getExtras().getString("nombreSierra");
        foto = getIntent().getExtras().getString("fotoSierra");

        cargarCollapsinToolbar();

        cargarTexto(info);
    }

    private void cargarTexto(String texto) {

        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(texto);
    }

    private void cargarCollapsinToolbar() {
        ViewCompat.setTransitionName(findViewById(R.id.appBarLayout_refugios), EXTRA_IMAGE);
        supportPostponeEnterTransition();


        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar_refugios);
        collapsingToolbarLayout.setTitle(nombre);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.morado));

        imagen = findViewById(R.id.imageViewToolbar_refugios);

        Picasso.with(this).load(foto.replaceAll("file/d/","uc?export=download&id=")
                .replace("/view","")).into(imagen);

        //imagen.setImageResource(R.drawable.refugio_polarda);
    }
}
