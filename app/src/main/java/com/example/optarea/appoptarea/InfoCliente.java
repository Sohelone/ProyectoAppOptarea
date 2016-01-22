package com.example.optarea.appoptarea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * Created by Jose on 13/01/2016.
 */
public class InfoCliente extends Activity {

    Button enviarInfo;
    SharedPreferences preferencias;
    EditText nombreContacto;
    EditText telefono;
    EditText CIF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_cliente);

        nombreContacto= (EditText)findViewById(R.id.personacontacto);
        telefono= (EditText)findViewById(R.id.telefono);
        CIF= (EditText)findViewById(R.id.CIF);

        enviarInfo = (Button) findViewById(R.id.enviarInfo);
        enviarInfo.setOnClickListener(opcionesFacturaLayout);

        preferencias = getSharedPreferences("PreferenciasMain", Context.MODE_PRIVATE);

    }

    private OnClickListener opcionesFacturaLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("Registrado", "Si");
            editor.commit();

            Intent intent = new Intent(InfoCliente.this, OpcionesFactura.class);
            startActivity(intent);
            finish();
        }
    };

}
