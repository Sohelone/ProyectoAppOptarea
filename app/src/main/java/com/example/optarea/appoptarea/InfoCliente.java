package com.example.optarea.appoptarea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

/**
 * Created by Jose on 13/01/2016.
 */
public class InfoCliente extends Activity {

    Button enviarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_cliente);

        enviarInfo = (Button) findViewById(R.id.enviarInfo);
        enviarInfo.setOnClickListener(opcionesFacturaLayout);

    }

    private OnClickListener opcionesFacturaLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InfoCliente.this, OpcionesFactura.class);
            startActivity(intent);
        }
    };

}
