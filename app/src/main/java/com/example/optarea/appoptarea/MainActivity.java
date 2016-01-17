package com.example.optarea.appoptarea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = (EditText) findViewById(R.id.mail);
        password1 = (EditText) findViewById(R.id.pass1);
        password2 = (EditText) findViewById(R.id.pass2);
        logo = (ImageView) findViewById(R.id.logo);
        enviar = (Button) findViewById(R.id.enviar);
        //mail.setOnClickListener(clickEditText);

        enviar.setOnClickListener(infoClienteLayout);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private OnClickListener enviarMailConfirmacion = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Focused", Toast.LENGTH_SHORT).show();
            mail.setText("");
        }
    };
    //Metodo para enviar la informacion al servidor
    private OnClickListener infoClienteLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, InfoCliente.class);
            startActivity(intent);
        }
    };

    private OnFocusChangeListener introducirMail = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                mail.setText("");
                //Toast.makeText(getApplicationContext(), "Focused", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(getApplicationContext(), mail.getText().charAt(0), Toast.LENGTH_SHORT).show();
                if (mail.length() < 1) {
                    mail.setText("Introduce tu correo");
                }
            }
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

    public void sendMessage(View view)    {

    }

}





