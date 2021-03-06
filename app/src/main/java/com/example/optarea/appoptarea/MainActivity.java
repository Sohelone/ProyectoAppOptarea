package com.example.optarea.appoptarea;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {

    //ImageView logo = (ImageView) findViewById(R.id.logo);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    EditText mail;
    EditText password1;
    EditText password2;
    ImageView logo;
    Button entrar;
    CheckBox recordar;


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences("PreferenciasMain", Context.MODE_PRIVATE);
        String confirmacion = sharedpreferences.getString("Registrado", "No");

        if (confirmacion.equals("Si")){
            setContentView(R.layout.pantalla_login);
        }
        else {
            Intent actividadRegistro = new Intent(MainActivity.this, PantallaRegistro.class);
            startActivity(actividadRegistro);
            finish();
        }

        Log.i("ComprobarPreferencias", confirmacion);

        //Toast.makeText(getApplicationContext(), confirmacion, Toast.LENGTH_LONG);

       // setContentView(R.layout.pantalla_login);

        mail = (EditText) findViewById(R.id.mail);
        password1 = (EditText) findViewById(R.id.pass1);
        password2 = (EditText) findViewById(R.id.pass2);
        logo = (ImageView) findViewById(R.id.logo);
        entrar = (Button) findViewById(R.id.entrar);
        recordar = (CheckBox) findViewById(R.id.checkBox);

        entrar.setOnClickListener(infoClienteLayout);





        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    //Metodo para entrar la informacion al servidor
    private OnClickListener infoClienteLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {

            if(entrar.getText().toString().equals("Entrar") && recordar.isChecked()==true){
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Registrado", "No");
                editor.commit();
            }

            Log.i("ComprobarEncoded", sharedpreferences.getString("Registrado", "Si"));

            Intent intent = new Intent(MainActivity.this, OpcionesFactura.class);
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





