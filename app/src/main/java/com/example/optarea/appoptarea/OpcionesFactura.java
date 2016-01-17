package com.example.optarea.appoptarea;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;

/**
 * Created by Jose on 13/01/2016.
 */

public class OpcionesFactura extends AppCompatActivity{

    Button energia;
    Button telecom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_factura);
        energia = (Button)findViewById(R.id.energia);
        telecom = (Button)findViewById(R.id.telecom);

        energia.setOnClickListener(MailLayout);
        telecom.setOnClickListener(SubirImagenLayout);


        //Typeface AgendaLight = Typeface.createFromAsset(getApplicationContext().getAssets(), "Agenda-Light.ttf");
        //energia.setTypeface(AgendaLight);


    }

    private OnClickListener MailLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OpcionesFactura.this, Email.class);
            startActivity(intent);
        }
    };

    private OnClickListener SubirImagenLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OpcionesFactura.this, SubirImagen.class);
            startActivity(intent);
        }
    };
}
