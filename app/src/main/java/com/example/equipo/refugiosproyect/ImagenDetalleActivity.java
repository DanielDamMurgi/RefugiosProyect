package com.example.equipo.refugiosproyect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.equipo.refugiosproyect.ClasesPrincipales.Imagen;

public class ImagenDetalleActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "ImagenDetalleActivity.IMAGEN";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_detalle);

        imageView = findViewById(R.id.iv_imagenDetalle);
        Imagen imagen = getIntent().getParcelableExtra(EXTRA_PHOTO);

        Glide.with(this)
                .load(imagen.getUrl())
                .asBitmap()
                .error(R.drawable.ic_account_circle_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
