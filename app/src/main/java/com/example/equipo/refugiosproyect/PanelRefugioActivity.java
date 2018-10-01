package com.example.equipo.refugiosproyect;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class PanelRefugioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imagen;
    private static final String EXTRA_IMAGE = "com.example.equipo.refugiosproyect.extraimage";
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_refugio);

        ViewCompat.setTransitionName(findViewById(R.id.appBarLayout_refugios),EXTRA_IMAGE);
        supportPostponeEnterTransition();

        toolbar = findViewById(R.id.toolbar_refugios);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar_refugios);
        collapsingToolbarLayout.setTitle("La Polarda");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.blanco));


        imagen = findViewById(R.id.imageViewToolbar_refugios);
        imagen.setImageResource(R.drawable.refugio_polarda);
    }

}
