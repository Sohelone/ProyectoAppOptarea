package com.example.optarea.appoptarea;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Jose on 18/01/2016.
 */
public class ConfirmarCaptura extends AppCompatActivity {

    ImageView captura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_captura);

        captura = (ImageView)findViewById(R.id.captura);

        Bundle extra = getIntent().getExtras();
        Bitmap imageBitmap = (Bitmap) extra.get("data");
        captura.setImageBitmap(imageBitmap);

    }
}
