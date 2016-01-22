package com.example.optarea.appoptarea;

/**
 * Created by Jose on 20/01/2016.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import static android.view.View.*;

public class PantallaRegistro extends AppCompatActivity {

    //ImageView logo = (ImageView) findViewById(R.id.logo);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    EditText mail;
    EditText password;
    Button aceptar;



    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro);

        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.pass1);
        aceptar = (Button) findViewById(R.id.aceptar);

        aceptar.setOnClickListener(infoClienteLayout);

    }

    //Metodo para entrar la informacion al servidor
    private OnClickListener infoClienteLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {

           //Aqui el envio de la info al server
            //Bundle datosRegistro =

            Intent intent = new Intent(PantallaRegistro.this, InfoCliente.class);
            startActivity(intent);
            finish();
        }
    };

    private void cifrarPassword(final EditText password){

        password.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password.setText("");
                    password.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //Toast.makeText(getApplicationContext(), "Focused", Toast.LENGTH_SHORT).show();
                } else if (!hasFocus) {
                    //Toast.makeText(getApplicationContext(), "Not Focused", Toast.LENGTH_SHORT).show();
                    if (password.length() < 1) {
                        password.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                        if(password.getId()== R.id.pass1) password.setText("Introduce contraseña");
                        else password.setText("Repite contraseña");
                    }
                }
            }
        });
    }

}