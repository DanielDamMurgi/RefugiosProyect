package com.example.equipo.refugiosproyect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textView = findViewById(R.id.textView_informacionSierras);

        cargarTexto("Sierra Nevada es un macizo montañoso perteneciente al conjunto de las Cordilleras Béticas, concretamente al Sistema Penibético. Está situada mayoritariamente en la provincia de Granada y, en su parte más oriental, se extiende hasta la provincia de Almería en la Región de Andalucía, España. Es el macizo montañoso de mayor altitud de toda Europa occidental, después de los Alpes. Su altitud máxima se alcanza en el pico Mulhacén, de 3.482 msnm. En 1986 fue declarada Reserva de la Biosfera por la UNESCO y en 1999 gran parte de su territorio fue declarado Parque nacional1\u200B por sus valores botánicos, paisajísticos y naturales.\n" +
                "\n" +
                "Sierra Nevada, junto con con las el resto de sierras Béticas, se formó durante la Orogénesis Alpina en la Era Terciaria. A causa de su aislamiento y altitud, desde el fin de la Glaciación de Würm el macizo ha quedado como refugio de innumerables plantas y endemismos impropios de las latitudes mediterráneas en las que se sitúa, contándose, según fuentes del Ministerio de Medio Ambiente de España,2\u200B 66 especies vegetales vasculares endémicas y otras 80 especies animales propias del lugar.\n" +
                "\n" +
                "En sus faldas se encuentra la estación de esquí de Sierra Nevada, la más meridional de Europa y de mayor altitud de España.\n" +
                "\n" +
                "Sierra Nevada es un macizo montañoso perteneciente al conjunto de las Cordilleras Béticas, concretamente al Sistema Penibético. Está situada mayoritariamente en la provincia de Granada y, en su parte más oriental, se extiende hasta la provincia de Almería en la Región de Andalucía, España. Es el macizo montañoso de mayor altitud de toda Europa occidental, después de los Alpes. Su altitud máxima se alcanza en el pico Mulhacén, de 3.482 msnm. En 1986 fue declarada Reserva de la Biosfera por la UNESCO y en 1999 gran parte de su territorio fue declarado Parque nacional1\u200B por sus valores botánicos, paisajísticos y naturales.\n" +
                "\n" +
                "Sierra Nevada, junto con con las el resto de sierras Béticas, se formó durante la Orogénesis Alpina en la Era Terciaria. A causa de su aislamiento y altitud, desde el fin de la Glaciación de Würm el macizo ha quedado como refugio de innumerables plantas y endemismos impropios de las latitudes mediterráneas en las que se sitúa, contándose, según fuentes del Ministerio de Medio Ambiente de España,2\u200B 66 especies vegetales vasculares endémicas y otras 80 especies animales propias del lugar. Sierra Nevada es un macizo montañoso perteneciente al conjunto de las Cordilleras Béticas, concretamente al Sistema Penibético. Está situada mayoritariamente en la provincia de Granada y, en su parte más oriental, se extiende hasta la provincia de Almería en la Región de Andalucía, España. Es el macizo montañoso de mayor altitud de toda Europa occidental, después de los Alpes. Su altitud máxima se alcanza en el pico Mulhacén, de 3.482 msnm. En 1986 fue declarada Reserva de la Biosfera por la UNESCO y en 1999 gran parte de su territorio fue declarado Parque nacional1\u200B por sus valores botánicos, paisajísticos y naturales.\n" +
                "\n" +
                "Sierra Nevada, junto con con las el resto de sierras Béticas, se formó durante la Orogénesis Alpina en la Era Terciaria. A causa de su aislamiento y altitud, desde el fin de la Glaciación de Würm el macizo ha quedado como refugio de innumerables plantas y endemismos impropios de las latitudes mediterráneas en las que se sitúa, contándose, según fuentes del Ministerio de Medio Ambiente de España,2\u200B 66 especies vegetales vasculares endémicas y otras 80 especies animales propias del lugar.\n" +
                "\n" +
                "En sus faldas se encuentra la estación de esquí de Sierra Nevada, la más meridional de Europa y de mayor altitud de España.\n" +
                "\n" +
                "Sierra Nevada es un macizo montañoso perteneciente al conjunto de las Cordilleras Béticas, concretamente al Sistema Penibético. Está situada mayoritariamente en la provincia de Granada y, en su parte más oriental, se extiende hasta la provincia de Almería en la Región de Andalucía, España. Es el macizo montañoso de mayor altitud de toda Europa occidental, después de los Alpes. Su altitud máxima se alcanza en el pico Mulhacén, de 3.482 msnm. En 1986 fue declarada Reserva de la Biosfera por la UNESCO y en 1999 gran parte de su territorio fue declarado Parque nacional1\u200B por sus valores botánicos, paisajísticos y naturales.\n" +
                "\n" +
                "Sierra Nevada, junto con con las el resto de sierras Béticas, se formó durante la Orogénesis Alpina en la Era Terciaria. A causa de su aislamiento y altitud, desde el fin de la Glaciación de Würm el macizo ha quedado como refugio de innumerables plantas y endemismos impropios de las latitudes mediterráneas en las que se sitúa, contándose, según fuentes del Ministerio de Medio Ambiente de España,2\u200B 66 especies vegetales vasculares endémicas y otras 80 especies animales propias del lugar.");
    }

    private void cargarTexto(String texto){

        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(texto);

    }
}
